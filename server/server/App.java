package server;

import java.net.*;
import java.io.*;
import util.*;


public class App {
    // default port
    // TODO: set port using cli arguments
    private static int port = 9595;

    private static Socket socket = null;
    private static ServerSocket serversocket = null;
    private static DataInputStream input = null;

    public static void main(String[] args) {
        // try to create a socket object
        // make sure you're running a server on ip:port
        // otherwise you'll get ConnectException
        try {
            serversocket = new ServerSocket(port);
            System.out.println("server bind to: " + port);
        } catch(ConnectException e) {
            System.err.println("couldn't connect; connection refused.");
            System.exit(2);
        } catch(IOException e) {
            System.err.println("io exception while creating server socket");
            System.exit(2);
        }

        try {
            // wait to accept
            // TODO: exception handling (maybe connection refuesed)
            socket = serversocket.accept();

            // to read client socket data
            input = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream())
                    );
        } catch(IOException e) {
            System.err.println("io exception while trying to create socket stream");
            System.exit(2);
        }
        
        String command;
        while (true) {
            try {
                command = input.readUTF();
                Parser.parse_exec(command);
            } catch(InvalidCommandException e) {
                System.err.println(e.getMessage());
            } catch(IOException e) {
                // clinet wants to quit, no more reading
                break;
            }
        }

        // close and free resources
        try {
            input.close();
            socket.close();
            serversocket.close();
        } catch(IOException e) {
            System.err.println("io exception while closing resources");
            System.exit(2);
        } 
    }
}

