# Herança e Polimorfismo em Java

## Herança

Herança é quando uma classe filha **herda atributos e métodos** de uma classe pai, reaproveitando e especializando o comportamento.

```java
// Classe pai
public abstract class Conta {

    protected String numero;
    protected BigDecimal saldo;

    public Conta(String numero, BigDecimal saldo) {
        this.numero = numero;
        this.saldo = saldo;
    }

    public void depositar(BigDecimal valor) {
        this.saldo = this.saldo.add(valor);
    }

    // cada filho implementa do seu jeito
    public abstract void sacar(BigDecimal valor);
}
```

```java
// Filho 1 — herda depositar(), especializa sacar()
public class ContaCorrente extends Conta {

    private BigDecimal limiteChequeEspecial;

    public ContaCorrente(String numero, BigDecimal saldo, BigDecimal limite) {
        super(numero, saldo);
        this.limiteChequeEspecial = limite;
    }

    @Override
    public void sacar(BigDecimal valor) {
        BigDecimal saldoTotal = saldo.add(limiteChequeEspecial);

        if (valor.compareTo(saldoTotal) > 0)
            throw new SaldoInsuficienteException("Limite do cheque especial excedido");

        this.saldo = this.saldo.subtract(valor);
    }
}
```

```java
// Filho 2 — herda depositar(), especializa sacar()
public class ContaPoupanca extends Conta {

    public ContaPoupanca(String numero, BigDecimal saldo) {
        super(numero, saldo);
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor.compareTo(saldo) > 0)
            throw new SaldoInsuficienteException("Saldo insuficiente na poupança");

        this.saldo = this.saldo.subtract(valor);
    }
}
```

---

## Polimorfismo

Polimorfismo é a capacidade de **tratar objetos diferentes pelo mesmo tipo pai**, deixando cada um se comportar do seu próprio jeito em tempo de execução.

```java
public class BancoService {

    // recebe Conta — não sabe (nem precisa saber) qual subtipo é
    public void realizarSaque(Conta conta, BigDecimal valor) {
        conta.sacar(valor);   // cada subtipo executa o seu sacar()
    }
}
```

```java
// Em uso — o mesmo método se comporta diferente para cada tipo
BancoService banco = new BancoService();

Conta corrente = new ContaCorrente("001", new BigDecimal("500"), new BigDecimal("200"));
Conta poupanca = new ContaPoupanca("002", new BigDecimal("500"));

banco.realizarSaque(corrente, new BigDecimal("650"));  // usa cheque especial → OK
banco.realizarSaque(poupanca, new BigDecimal("650"));  // sem limite extra → exceção
```

A chamada `conta.sacar()` é a mesma — quem decide o comportamento é o objeto real em memória, não o tipo da variável.

---

## A diferença em uma linha

| Conceito | O que faz |
|---|---|
| **Herança** | Classe filha reutiliza e especializa o que a pai definiu |
| **Polimorfismo** | O mesmo código funciona com qualquer subtipo, cada um reagindo do seu jeito |

> Eles andam juntos: a herança cria a hierarquia, o polimorfismo a explora.