package bank.management.dash;

public class TransactionModel {
    private String cardNumber;
    private String date;
    private String type;
    private double amount;

    public TransactionModel(String cardNumber, String date, String type, double amount) {
        this.cardNumber = cardNumber;
        this.date = date;
        this.type = type;
        this.amount = amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

