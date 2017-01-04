/**
 * Created by AndrewIrwin on 01/01/2017.
 */
public class TransactionHistory {

    private String accNumber;
    private int last10Transactions[];






    public TransactionHistory(String accNumber, int[] last10Transactions) {
        this.accNumber = accNumber;
        this.last10Transactions = last10Transactions;
    }










    public String getaccNumber() {
        return accNumber;
    }

    public void setaccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public int[] getLast10Transactions() {
        return last10Transactions;
    }

    public void setLast10Transactions(int[] last10Transactions) {
        this.last10Transactions = last10Transactions;
    }
}
