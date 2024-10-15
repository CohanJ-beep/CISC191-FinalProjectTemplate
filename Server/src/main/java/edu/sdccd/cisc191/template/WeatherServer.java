package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class WeatherServer {

    private ServerSocket serverSocket;

    /**
     * creates a connection with the client and handles the request
     * @param port
     */
    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        System.out.println("Server is listening on port " + port);

        while (true) {
            try {
                // accepts client connection
                Socket socket = serverSocket.accept();

                // handles the client request
                handleClientRequest(socket);
            } catch (IOException e) {
                System.out.println("Error connecting with client: " + e.getMessage());
            }
        }
    }

    /**
     * completes a series of tasks to handle the client's request
     * @param clientSocket the client socket to receive client info
     */
    private static void handleClientRequest(Socket clientSocket) {
        try (ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {

            //receives request from client
            CustomerRequest request = (CustomerRequest) in.readObject();
            //receives the user input
            String city = request.getCity();

            //creates a response
            CustomerResponse response = new CustomerResponse(city);

            //sends the server response to the client
            out.writeObject(response);
            out.flush();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * initiates the networking
     */
    public static void main(String[] args) {
        WeatherServer server = new WeatherServer();
        try {
            server.start(12345);
            System.out.println("Server running at port 12345");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

