package client;

import java.net.*;
import java.io.*;

public class App {
    // default ip and port
    // TODO: set ip:port using cli arguments
    private String ip = "127.0.0.1";
    private int port = 9595;

    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

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
        }

        // init IO streams
        try {
            input = new DataInputStream(System.in);
            // to write/send user commands to socket server
            output = new DataOutputStream(socket.getOutputStream());
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
            } catch(IOException e) {
                break; // user wanna exit, don't read anymore
            }
        }

        // close and free resources
        input.close();
        output.close();
        socket.close();
    }
}
