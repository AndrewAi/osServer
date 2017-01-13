


import java.io.Serializable;
import java.util.*;


/**
 * Created by AndrewIrwin on 01/01/2017.
 */

// implements Serilizable so that the objects can be written to file in EchoServer class.
public class Account implements Serializable {


    // Member Variables
    private String Name;
    private String Address;
    private String accountNumber;
    private String userName;
    private String password;
    private double balance;
    private Queue<String> transactions = new LinkedList<String>();


    // Constructor. use to construct object whenver an account is created.
    public Account(String name, String address, String accountNumber, String userName, String password, double balance) {
        Name = name;
        Address = address;
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }


    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getaccountNumber() {
        return accountNumber;
    }

    public void setaccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getBalance() {
        return balance;
    }

    // one method used to lodge and withdraw.
    public void setBalance(int option, double balance) {


        if (option == 1)
            this.balance += balance;


        else if (option == 2)
            this.balance -= balance;
    }


    // addTransaction used to record last 10 transactions history
    public void addTransaction(int option, double transaction) {


        String qTransaction;


        if (transactions.size() >= 10)
            transactions.remove();


        qTransaction = Double.toString(transaction);

        if (option == 1) {
            qTransaction = "+" + qTransaction;
        } else if (option == 2) {
            qTransaction = "-" + qTransaction;
        }

        transactions.add(qTransaction);

    }


    // returns a Queue of type string that contains the last 10 transactions on the account.
    public Queue<String> getLast10Transactions() {
        return transactions;
    }


}
