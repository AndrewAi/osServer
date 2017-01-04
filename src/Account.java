/**
 * Created by AndrewIrwin on 01/01/2017.
 */
public class Account {


    private String Name;
    private String Address;
    private String accNumber;
    private String userName;
    private String password;
    private double balance;





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

    public void setBalance(double balance) {
        this.balance = balance;
    }




}
