package client;

import java.net.*;
import java.io.*;

public class App {
    // default ip and port
    // TODO: set ip:port using cli arguments
    private static String ip = "127.0.0.1";
    private static int port = 9595;

    private static Socket socket = null;
    private static DataInputStream input = null;
    private static DataOutputStream output = null;
    private static DataInputStream input_from_server = null; // read data from server

    public static void main(String[] args) {
        // try to create a socket object
        // make sure you're running a server on ip:port
        // otherwise you'll get ConnectException
        try {
            socket = new Socket(ip, port);
            System.out.println("client connected to: " + ip + ":" + port);
        } catch(ConnectException e) {
            System.err.println("couldn't connect; connection refused.");
            System.exit(2);
        } catch(UnknownHostException e) {
            System.err.println("you're trying to connect to an unknown host.");
            System.exit(2);
        } catch(IOException e) {
            System.err.println("io exception occured while conecting to server.");
            System.exit(2);
        }

        // init IO streams
        try {
            input = new DataInputStream(System.in);
            // to write/send user commands to socket server
            output = new DataOutputStream(socket.getOutputStream());
            // to read from server
            input_from_server = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
                    );
        } catch(IOException e) {
            System.err.println("IO streams init failed.");
            System.exit(1);
        }

        // repl
        String command;
        while(true) {
            try {
                System.out.printf(">>> ");
                command = input.readLine();
                // command arguments can be also utf (Eg. name in farsi)
                output.writeUTF(command);
                // read and print result (data from server)
                System.out.println(
                        input_from_server.readUTF());
            } catch(IOException e) {
                break; // user wanna exit, don't read anymore
            }
        }

        // close and free resources
        try {
            input.close();
            output.close();
            socket.close();
        } catch(IOException e) {
            System.err.println("io exception occured while closing resources.");
            System.exit(2);
        }
    }
}
