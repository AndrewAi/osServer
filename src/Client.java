/**
 * Created by AndrewIrwin on 01/01/2017.
 */


import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    Socket requestSocket;
    ObjectOutputStream out;
    ObjectInputStream in;
    String message = "";
    String ipaddress;
    Scanner stdin;

    Client() {
    }

    void run() {

        // IP ADDRESS
        //   192.168.1.103
        //  192.168.1.104
        // 192.168.1.102


        stdin = new Scanner(System.in);
        try {
            //1. creating a socket to connect to the server
            System.out.println("Please Enter your IP Address");
            ipaddress = stdin.next();
            // create a socket bewtween client and ip address of the server on port 2004
            requestSocket = new Socket(ipaddress, 2004);
            System.out.println("Connected to " + ipaddress + " in port 2004");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server

            // do while loop, while user input (message) is not equal to exit
            do {
                try {
                    // read in message sent from server and display it to the user
                    message = (String) in.readObject();
                    System.out.println(message);

                    // send user input to the server to be acted on.
                    message = stdin.next();
                    sendMessage(message);


                } catch (ClassNotFoundException classNot) {
                    System.err.println("data received in unknown format");
                }
            } while (!message.equals("exit"));
        } catch (UnknownHostException unknownHost) {
            System.err.println("You are trying to connect to an unknown host!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        // after while loop has finished close connection to server
        finally {
            //4: Closing connection
            try {
                in.close();
                out.close();
                requestSocket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    // method used to send message to server
    void sendMessage(String msg) {
        try {
            out.writeObject(msg);
            out.flush();
            // System.out.println("client>>>" + msg);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    // when the main method is called, create a new instance of the Requester class.
    // and call its run method. which  is located above,
    public static void main(String args[]) {
        Client client = new Client();
        client.run();
    }
}
