


import java.io.Serializable;
import java.util.*;


/**
 * Created by AndrewIrwin on 01/01/2017.
 */
public class Account implements Serializable {


    private String Name;
    private String Address;
    private String accNumber;
    private String userName;
    private String password;
    private double balance;
    private Queue<String> q = new LinkedList<String>();






    public Account(String name, String address, String accNumber, String userName, String password, double balance) {
        Name = name;
        Address = address;
        this.accNumber = accNumber;
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

    public String getaccNumber() {
        return accNumber;
    }

    public void setaccNumber(String accNumber) {
        this.accNumber = accNumber;
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

    public void setBalance(int option, double balance) {



        if (option == 1)
        this.balance += balance;


        else if (option == 2)
            this.balance -= balance;
    }





    public void addTransaction(int option, double transaction){

        String qTransaction;


        if (q.size() >= 10)
            q.remove();


        qTransaction = Double.toString(transaction);

        if (option == 1) {
            qTransaction = "+" + qTransaction;
        }

        else if (option == 2) {
            qTransaction = "-" + qTransaction;
        }

        q.add(qTransaction);

    }


    public Queue<String> getLast10Transactions() {
        return q;
    }


}
