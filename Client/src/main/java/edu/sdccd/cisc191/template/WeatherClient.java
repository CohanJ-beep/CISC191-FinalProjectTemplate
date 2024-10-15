package edu.sdccd.cisc191.template;
/**
 * This program opens a connection to a computer specified
 * as the first command-line argument.  If no command-line
 * argument is given, it prompts the user for a computer
 * to connect to.  The connection is made to
 * the port specified by LISTENING_PORT.  The program reads one
 * line of text from the connection and then closes the
 * connection.  It displays the text that it read on
 * standard output.  This program is meant to be used with
 * the server program, DateServer, which sends the current
 * date and time on the computer where the server is running.
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WeatherClient {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Socket clientSocket;
    private static WeatherLocation[] cities;
    private static WeatherReport [][] weatherReports;
    private int index;
    private WeatherReport[] weatherArray;

    /**
     * creates a connection with the server and handles requests and responses
     * @param ip device name
     * @param port port number
     */
    public void startConnection(String ip, int port) throws Exception {
        //creates a connection with a server
        clientSocket = new Socket(ip, port);

        //creates streams to receive and send objects
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

        //creates object to send over
        CustomerRequest request = new CustomerRequest();
        // Send the city name to the server
        out.writeObject(request);
        out.flush();

        // Receive weather reports from the server
        CustomerResponse reports = (CustomerResponse) in.readObject();

        reports.findIndex();
        index = reports.getIndex();
        //populates the cities 1d array
        cities = reports.getCities();
        CustomerResponse.loadWeatherReports();
        //populates the weatherReports 2d array
        weatherReports = reports.getWeatherReports();

        //gets 1d array
        weatherArray = weatherReports[index];

        //uses information from the received and requested objects to print out a response
        handleResponse(request.getCity());
    }

    /**
     * Prints out the response sent by the server
     * @param cityName name of city
     */
    public void handleResponse(String cityName){
        for (WeatherReport report : weatherArray) {
            System.out.println(report);
        }
    }

    /**
     * Stops the connection by closing the streams and socket connection
     */
    public void stopConnection() throws IOException {
        out.close();
        in.close();
        clientSocket.close();
    }

    /**
     * Initiates the networking
     */
    public static void main(String[] args) {
        WeatherClient client = new WeatherClient();
        try {
            client.startConnection("localhost", 12345);
            client.stopConnection();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}