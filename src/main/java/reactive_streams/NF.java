package reactive_streams;

public class NF {

    private String cliente;
    private String book;
    private double valor;

    public NF(String cliente, String book, double valor) {
        this.cliente = cliente;
        this.book = book;
        this.valor = valor;
    }

    public boolean hasValidAmount() {
        return valor > 0;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public NF toUpperCliente() {
        this.setCliente(this.getCliente().toUpperCase());
        return this;
    }

    @Override
    public String toString() {
        return "NF{" +
                "cliente='" + cliente + '\'' +
                ", book='" + book + '\'' +
                ", valor=" + valor +
                '}';
    }
}
