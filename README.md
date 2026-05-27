# Serviço de Transferência Bancária

Serviço backend responsável por gerenciar transferências bancárias entre contas no ecossistema bancario. 
Cobre o ciclo completo de uma transferência: validação de saldo, verificação de limites, execução atômica 
do débito/crédito e registro do histórico de transações.

---

## Sumário

- [Visão Geral](#visão-geral)
- [Tipos de Conta](#tipos-de-conta)
- [Modelo de Domínio](#modelo-de-domínio)
- [Regras de Negócio](#regras-de-negócio)
- [Tecnologias](#tecnologias)
- [Como Executar](#como-executar)
- [Endpoints](#endpoints)
- [Tratamento de Erros](#tratamento-de-erros)

---

## Visão Geral

```
Cliente ──── possui ──── Conta ──── realiza ──── Transferência
                          │
                    (débito / crédito)
```

O fluxo de uma transferência segue estas etapas:

1. Validação dos dados da requisição (Bean Validation)
2. Verificação de existência das contas de origem e destino
3. Checagem de saldo disponível e limite diário
4. Execução atômica com `@Transactional` (débito + crédito)
5. Persistência do registro de transferência
6. Notificação assíncrona ao cliente (evento Kafka)

---

## Tipos de Conta

O bancario oferece os seguintes tipos de conta. Nem todos estão disponíveis para uso neste serviço:

| Tipo | Descrição | Disponível neste serviço |
|---|---|---|
| `CONTA_CORRENTE` | Conta principal do cliente pessoa física. Permite Pix, TED e transferências internas. | ✅ Sim |
| `CONTA_PJ` | Conta para pessoa jurídica (MEI, empresas). Possui limites e regras próprias. | ✅ Sim |
| `CONTA_INVESTIMENTO` | Conta vinculada a produtos de investimento (RDB, CDB). Não permite transferências diretas entre clientes. | ❌ Não |
| `CONTA_CREDITO` | Associada ao cartão de crédito bancario. Não é uma conta transacional. | ❌ Não |

> Transferências só podem ser realizadas entre contas dos tipos `CONTA_CORRENTE` e `CONTA_PJ`.

---

## Modelo de Domínio

### Cliente

Representa a pessoa física ou jurídica titular da conta.

```java
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @NotBlank(message = "CPF/CNPJ é obrigatório")
    @Column(name = "documento", nullable = false, unique = true, length = 14)
    private String documento;

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "E-mail inválido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull(message = "Tipo de pessoa é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;  // FISICA, JURIDICA

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Conta conta;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();
}
```

### Conta

Associada a exatamente um cliente. Armazena saldo, tipo e limites de transferência.

```java
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @Column(name = "agencia", nullable = false, length = 4)
    private String agencia;

    @NotNull
    @Column(name = "numero", nullable = false, unique = true, length = 10)
    private String numero;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false)
    private TipoConta tipo;  // CONTA_CORRENTE, CONTA_PJ

    @NotNull
    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    @NotNull
    @Column(name = "limite_diario", nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteDiario;

    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false, unique = true)
    private Cliente cliente;

    @Column(name = "ativa", nullable = false)
    private boolean ativa = true;

    public void setBalance(BigDecimal saldo) {
        if (saldo != null && saldo.compareTo(BigDecimal.ZERO) >= 0)
            this.saldo = saldo;
    }

    public boolean temSaldoSuficiente(BigDecimal valor) {
        return this.saldo.compareTo(valor) >= 0;
    }
}
```

### TipoConta (Enum)

```java
public enum TipoConta {

    CONTA_CORRENTE("Conta Corrente", true),
    CONTA_PJ("Conta Pessoa Jurídica", true),
    CONTA_INVESTIMENTO("Conta Investimento", false),
    CONTA_CREDITO("Conta Crédito", false);

    private final String descricao;
    private final boolean permiteTransferencia;

    TipoConta(String descricao, boolean permiteTransferencia) {
        this.descricao = descricao;
        this.permiteTransferencia = permiteTransferencia;
    }

    public boolean permiteTransferencia() {
        return permiteTransferencia;
    }
}
```

### Transferência

```java
@Entity
@Table(name = "transferencias")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "conta_origem_id", nullable = false)
    private Conta contaOrigem;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "conta_destino_id", nullable = false)
    private Conta contaDestino;

    @NotNull
    @Positive(message = "O valor deve ser maior que zero")
    @Column(name = "valor", nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusTransferencia status;  // PENDENTE, CONCLUIDA, FALHA

    @Column(name = "realizada_em", nullable = false)
    private LocalDateTime realizadaEm = LocalDateTime.now();
}
```

---

## Regras de Negócio

- Apenas contas do tipo `CONTA_CORRENTE` e `CONTA_PJ` podem realizar e receber transferências
- Conta de origem e destino não podem ser a mesma
- O saldo da conta de origem deve ser suficiente para cobrir o valor da transferência
- O valor da transferência não pode ultrapassar o limite diário da conta de origem
- Contas inativas não podem participar de transferências
- Clientes inativos não podem realizar transferências
- Toda transferência é executada dentro de uma transação atômica (`@Transactional`)

---

## Tecnologias

| Tecnologia | Versão | Uso |
|---|---|---|
| Java | 21 | Linguagem principal |
| Spring Boot | 3.3.x | Framework base |
| Spring Data JPA | 3.3.x | Persistência |
| Hibernate | 6.x | ORM |
| PostgreSQL | 15 | Banco de dados |
| Bean Validation | 3.x | Validação de campos |
| Kafka | 3.x | Notificações assíncronas |
| Docker | — | Containerização |
| JUnit 5 | 5.10.x | Testes |

---

## Como Executar

### Pré-requisitos

- Java 21+
- Docker e Docker Compose

### Subindo o ambiente

```bash
# Clone o repositório
git clone https://github.com/bancario/nu-transfer.git
cd nu-transfer

# Sobe PostgreSQL e Kafka via Docker
docker compose up -d

# Executa a aplicação
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## Endpoints

### Criar cliente com conta

```http
POST /clientes
Content-Type: application/json

{
  "nome": "Ana Silva",
  "documento": "123.456.789-00",
  "email": "ana@email.com",
  "tipoPessoa": "FISICA",
  "conta": {
    "tipo": "CONTA_CORRENTE",
    "limiteDiario": 5000.00
  }
}
```

### Realizar transferência

```http
POST /transferencias
Content-Type: application/json

{
  "contaOrigemId": "uuid-da-conta-origem",
  "contaDestinoId": "uuid-da-conta-destino",
  "valor": 250.00
}
```

### Consultar histórico

```http
GET /transferencias?contaId=uuid-da-conta&page=0&size=10
```

---

## Tratamento de Erros

| Código HTTP | Situação |
|---|---|
| `400 Bad Request` | Dados inválidos ou campo obrigatório ausente |
| `404 Not Found` | Conta ou cliente não encontrado |
| `422 Unprocessable Entity` | Saldo insuficiente, limite excedido ou tipo de conta inválido |
| `409 Conflict` | Transferência duplicada detectada |
| `500 Internal Server Error` | Falha inesperada — transação revertida automaticamente |

---

## Licença

Projeto de fins educacionais. Não possui vínculo oficial com o bancario.