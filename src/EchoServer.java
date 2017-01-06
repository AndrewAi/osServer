/**
 * Created by AndrewIrwin on 01/01/2017.
 */

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket m_ServerSocket = new ServerSocket(2004, 10);
        int id = 0;
        while (true) {
            Socket clientSocket = m_ServerSocket.accept();
            ClientServiceThread cliThread = new ClientServiceThread(clientSocket, id++);
            cliThread.start();
        }
    }
}

class ClientServiceThread extends Thread {
    Socket clientSocket;
    String clientResponse;
    int clientID = -1;
    boolean running = true;
    ObjectOutputStream out;
    ObjectInputStream in;

    ClientServiceThread(Socket s, int i) {
        clientSocket = s;
        clientID = i;
    }


    private ArrayList<Account> accounts = new ArrayList<Account>();
    private int accountLocation = 0;
    private String name;
    private String address;
    private String accNumber;
    private String userName;
    private String password;


    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            System.out.println("client>> " + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void run() {
        System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                + clientSocket.getInetAddress().getHostName());
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                    + clientSocket.getInetAddress().getHostName());


            do {
                try {


                   mainMenu();


                } catch (ClassNotFoundException classnot) {
                    System.err.println("Data received in unknown format");
                }

            } while (!clientResponse.equals("bye"));

            System.out.println("Ending Client : ID - " + clientID + " : Address - "
                    + clientSocket.getInetAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void mainMenu() throws IOException, ClassNotFoundException {


        // read object from file here
        // add object to array list

        // when reading in object from file check to see account number does not already exist
        // in object array list.

        // clear accounts list
        // then read in objects from file and add to accounts list



        sendMessage("Welcome, Enter 1 to Register, 2 to Login.");
        clientResponse = (String) in.readObject();



        if (clientResponse.contains("1")) {
            register();
        } else if (clientResponse.contains("2")) {
            login();
        }


    }


    public void register() throws IOException, ClassNotFoundException {


        // enter user name and details etc.



        try {


            sendMessage("Please enter your Name: ");
            clientResponse = (String) in.readObject();
            name = clientResponse;


            sendMessage("Please Enter Address: ");
            clientResponse = (String) in.readObject();
            address = clientResponse;


            accNumber = UUID.randomUUID().toString();


            sendMessage("Please Enter username: ");
            clientResponse = (String) in.readObject();
            userName = clientResponse;


            sendMessage("Please Enter Password: ");
            clientResponse = (String) in.readObject();
            password = clientResponse;


            //printout details

            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("accNumber: " + accNumber);
            System.out.println("UserName: " + userName);
            System.out.println("Password: " + password);


            // prompt user to confirm details are correct
            // or re-enter them


            // print object to file here
            // set temp object then write temp object to file
            accounts.add(new Account(name, address, accNumber, userName, password, 0));






        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Account Created Successfully!");
        sendMessage("\nAccount Created Successfully! press any key to continue");
        clientResponse = (String) in.readObject();


        sendMessage("Press 1 to login, Press 2 Create another Account");
        clientResponse = (String) in.readObject();

        if (clientResponse.equalsIgnoreCase("1")) {

            try {
                login();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        else if (clientResponse.equalsIgnoreCase("2")){
            register();
        }

    }


    public void login() throws IOException, ClassNotFoundException {


        boolean userNameFound = false;
        boolean passwordFound = false;

        // check user account array to see if user exists.
        // if user does not exist prompt them to create an account, if yes call the register method

        //sendMessage("///////  LOGIN  ///////////");

        sendMessage("Please Enter username: ");
        clientResponse = (String) in.readObject();
        userName = clientResponse;


        while (userNameFound == false) {

            for (int i = 0; i < accounts.size(); i++) {

                if (accounts.get(i).getUserName().equalsIgnoreCase(userName)) {
                    userNameFound = true;

                }
                if (i == accounts.size()) {
                    sendMessage("UserName not found.");
                    break;
                }
            }
        }


        if (userNameFound) {

            sendMessage("Please Enter Password: ");
            clientResponse = (String) in.readObject();
            password = clientResponse;


            while (passwordFound == false) {

                for (int i = 0; i < accounts.size(); i++) {

                    if (accounts.get(i).getPassword().equalsIgnoreCase(password)) {
                        passwordFound = true;
                        accountLocation = i;


                    }

                    if (i == accounts.size()) {
                        sendMessage("Incorrect Password.");
                        break;

                    }
                }

            }

        }


        if (userNameFound && passwordFound == true) {


            System.out.println("User Logged in!");
            showLoggedInMenu();
        } else if (userNameFound == false) {
            login();
        } else if (passwordFound == false) {
            login();
        }



        // logout

    }


    public void showLoggedInMenu() throws IOException, ClassNotFoundException {

        String menuInput = "";

        sendMessage("\nAvailable Balance: € " + accounts.get(accountLocation).getBalance()
                + "\n 1. Change your Account Details "
                + "\n 2. Make Lodgement "
                + "\n 3. Make Withdrawal "
                + "\n 4. View last 10 Transactions "
                + "\n 5. Logout ");
        try {
            menuInput = (String) in.readObject();
            //int a = (Integer) in.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (menuInput.equalsIgnoreCase("1")) {
            System.out.println("Option 1 Selected");
            changeAccountDetails();
        }


        if (menuInput.equalsIgnoreCase("2")) {
            System.out.println("Option 2 Selected ");
            lodgement();
        }


        if (menuInput.equalsIgnoreCase("3")) {
            System.out.println("Option 3 Selected");
            withDrawal();
        }


        if (menuInput.equalsIgnoreCase("4")) {
            System.out.println("Option 4 Selected");
            viewLast10Transactions();
        }

        if (menuInput.equalsIgnoreCase("5")) {
            System.out.println("Option 5 Selected");
            mainMenu();
        }


    }


    public void changeAccountDetails() throws IOException, ClassNotFoundException {

        sendMessage("\n Change Details: " +
                "\n Enter 1: Change Name " +
                "\n Enter 2: Change Address " +
                "\n Enter 3: Change Username " +
                "\n Enter 4: Change Password ");

        int changeDetailsOption = Integer.parseInt((String) in.readObject());


        switch (changeDetailsOption) {

            case 1:
                sendMessage("Please enter your Name, Current: " + accounts.get(accountLocation).getName());
                clientResponse = (String) in.readObject();
                name = clientResponse;

                accounts.get(accountLocation).setName(name);
                sendMessage("Name Changed Successfully, Press 1 to Return to Menu");
                clientResponse = (String) in.readObject();
                break;


            case 2:
                sendMessage("Please Enter Address, Current: " + accounts.get(accountLocation).getAddress());
                clientResponse = (String) in.readObject();
                address = clientResponse;

                accounts.get(accountLocation).setAddress(address);
                sendMessage("Address Changed Successfully, Press 1 to Return to Menu");
                clientResponse = (String) in.readObject();
                break;


            case 3:
                sendMessage("Please Enter username, Current: " + accounts.get(accountLocation).getUserName());
                clientResponse = (String) in.readObject();
                userName = clientResponse;

                accounts.get(accountLocation).setUserName(userName);
                sendMessage("UserName Changed Successfully, Press 1 to Return to Menu");
                clientResponse = (String) in.readObject();
                break;


            case 4:
                sendMessage("Please Enter Password, Current: " + accounts.get(accountLocation).getPassword());
                clientResponse = (String) in.readObject();
                password = clientResponse;

                accounts.get(accountLocation).setPassword(password);
                sendMessage("Password Changed Successfully, Press 1 to Return to Menu");
                clientResponse = (String) in.readObject();
                break;

        }

        showLoggedInMenu();

    }

    public void lodgement() throws IOException, ClassNotFoundException {

        double amount;

        sendMessage("Please Enter Lodgement Amount: ");
        amount = Double.parseDouble((String) in.readObject());

        accounts.get(accountLocation).setBalance(1, amount);
        accounts.get(accountLocation).addTransaction(1, amount);

        sendMessage("Amount €" + amount + " Lodged Succesfully. Press 1 to return to Menu");
        clientResponse = (String) in.readObject();


        showLoggedInMenu();
    }


    public void withDrawal() throws IOException, ClassNotFoundException {

        double amount;


        sendMessage("\nPlease Enter Withdrawal Amount: ");
        amount = Double.parseDouble((String) in.readObject());

        if (accounts.get(accountLocation).getBalance() - amount <= -1000){

            sendMessage("Sorry Insufficient funds, €1000 credit limit reached!, Press any key to return.");
            clientResponse = (String) in.readObject();

            showLoggedInMenu();
        }

        accounts.get(accountLocation).setBalance(2, amount);
        accounts.get(accountLocation).addTransaction(2, amount);

        sendMessage("\nAmount €" + amount + " Withdrawn Successfully. Press 1 to return to Menu");
        clientResponse = (String) in.readObject();


        showLoggedInMenu();
    }


    public void viewLast10Transactions() throws IOException, ClassNotFoundException {


        sendMessage("Q: " + accounts.get(accountLocation).getLast10Transactions() + " Press 1 to return to menu");
        clientResponse = (String) in.readObject();


        showLoggedInMenu();

    }

}
