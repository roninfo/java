package bookstore.model;

public class NF {

    private String client;
    private String book;
    private double amount;

    public NF(String client, String book, double amount) {
        this.client = client;
        this.book = book;
        this.amount = amount;
    }

    public boolean hasValidAmount() {
        return amount > 0;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public NF toUpperCliente() {
        this.setClient(this.getClient().toUpperCase());
        return this;
    }

    @Override
    public String toString() {
        return "NF{" +
                "client='" + client + '\'' +
                ", book='" + book + '\'' +
                ", amount=" + amount +
                '}';
    }
}
