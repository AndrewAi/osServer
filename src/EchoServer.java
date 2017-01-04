/**
 * Created by AndrewIrwin on 01/01/2017.
 */

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket m_ServerSocket = new ServerSocket(2004,10);
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
    String message;
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



    void sendMessage(String msg)
    {
        try{
            out.writeObject(msg);
            out.flush();
            System.out.println("client>> " + msg);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }
    public void run() {
        System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                + clientSocket.getInetAddress().getHostName());
        try
        {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Accepted Client : ID - " + clientID + " : Address - "
                    + clientSocket.getInetAddress().getHostName());

            //sendMessage("Connection successful");
            sendMessage("Welcome, Enter 1 to Register, 2 to Login.");
            do{
                try
                {




                   // System.out.println("client>"+clientID+"  "+ message);
                    //if (message.equals("bye"))
                    //sendMessage("server got the following: "+message);
                    message = (String)in.readObject();

                    //if (message.contains("start")){
                        //sendMessage("Welcome, Enter 1 to Register, 2 to Login.");
                    //}

                    if (message.contains("1")){
                         register();
                    }

                    else if (message.contains("2")){
                        login();
                    }



                }
                catch(ClassNotFoundException classnot){
                    System.err.println("Data received in unknown format");
                }

            }while(!message.equals("bye"));

            System.out.println("Ending Client : ID - " + clientID + " : Address - "
                    + clientSocket.getInetAddress().getHostName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void register() throws IOException, ClassNotFoundException {

        Scanner console = new Scanner(System.in);


        // enter user name and details etc.

        //System.out.println("Please enter your Name: ");
        //name = console.nextLine();

        try {


            sendMessage("Please enter your Name: ");
            message = (String)in.readObject();
            name = message;


            sendMessage("Please Enter Address: ");
            message = (String)in.readObject();
            address = message;


             accNumber = UUID.randomUUID().toString();


            sendMessage("Please Enter username: ");
            message = (String)in.readObject();
            userName = message;


            sendMessage("Please Enter Password: ");
            message = (String)in.readObject();
            password = message;


            //printout details

            System.out.println("Name: " + name);
            System.out.println("Address: " + address);
            System.out.println("accNumber: " + accNumber);
            System.out.println("UserName: " + userName);
            System.out.println("Password: " + password);



            // prompt user to confirm details are correct
            // or re-enter them


            accounts.add(new Account(name, address, accNumber, userName, password, 0));


        }
        catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Account Created Successfully!");
        sendMessage("\nAccount Created Successfully! press any key to continue");
        message = (String)in.readObject();


        sendMessage("Press 1 to login");
        message = (String)in.readObject();

        try {
            login();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // then store as object in array.

        // then user account is created.

        // login method.

    }


    public void login() throws IOException, ClassNotFoundException {


        boolean userNameFound = false;
        boolean passwordFound = false;

        // check user account array to see if user exists.
        // if user does not exist prompt them to create an account, if yes call the register method

       //sendMessage("///////  LOGIN  ///////////");

        sendMessage("Please Enter username: ");
        message = (String)in.readObject();
        userName = message;



        while (userNameFound == false){

            for (int i = 0; i <accounts.size(); i++){

                if (accounts.get(i).getUserName().equalsIgnoreCase(userName)){
                    userNameFound = true;

                }
                if (i == accounts.size()){
                    sendMessage("UserName not found.");
                    break;
                }
            }
        }


        if (userNameFound){

            sendMessage("Please Enter Password: ");
            message = (String)in.readObject();
            password = message;



            while (passwordFound == false){

                for (int i = 0; i <accounts.size(); i++){

                    if (accounts.get(i).getPassword().equalsIgnoreCase(password)){
                        passwordFound = true;
                        accountLocation = i;


                    }

                    if (i == accounts.size()){
                        sendMessage("Incorrect Password.");
                        break;

                    }
                }

            }

        }


        if (userNameFound && passwordFound == true){


            System.out.println("User Logged in!");
            showLoggedInMenu();
        }
        else if (userNameFound == false){
            login();
        }
        else  if (passwordFound == false){
            login();
        }



        // if the user exists ask for username and password and if they match the ones in the array grant them access

        //once logged in the user will be displayed with their balance and a new menu.
        // make lodgement
        // make withdrawel
        // view last 10 transactions.
        // logout

    }




    public void showLoggedInMenu(){

        String menuInput = "";

        sendMessage("\nAvailable Balance: € " + accounts.get(accountLocation).getBalance()
                    +"\n 1. Change your Account Details "
                    +"\n 2. Make Lodgement "
                    +"\n 3. Make Withdrawel "
                    +"\n 4. View last 10 Transactions");
        try {
            menuInput = (String)in.readObject();
            //int a = (Integer) in.readObject();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (menuInput.equalsIgnoreCase("1"))
            System.out.println("Option 1 Selected");

        if (menuInput.equalsIgnoreCase("2"))
            System.out.println("Option 2 Selected ");

        if (menuInput.equalsIgnoreCase("3"))
            System.out.println("Option 3 Selected");

        if (menuInput.equalsIgnoreCase("4"))
            System.out.println("Option 4 Selected");


    }

}
