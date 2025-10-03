public class Transaction {

    private String operation;
    private double amount;
    private double balance;

    public static final String DEPOSIT = "deposit";
    public static final String WITHDRAW = "withdraw";

    /**
     * constructor.
     */
    public Transaction(String operation, double amount, double balance) {
        this.operation = operation;
        this.amount = amount;
        this.balance = balance;
    }

    /**
     * operation getter.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * operation setter.
     */
    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * amount getter.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * amount setter.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * balance getter.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * balance setter.
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}