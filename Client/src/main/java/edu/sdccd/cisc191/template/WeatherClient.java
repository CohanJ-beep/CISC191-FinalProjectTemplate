package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class WeatherClient {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 12345);
             ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);

            // Ask user to enter a city name
            System.out.println("Enter the name of the city (San Diego or New York): ");
            String city = scanner.nextLine();

            // Send the city name to the server
            out.writeObject(city);

            // Receive weather reports from the server
            WeatherReport[] reports = (WeatherReport[]) in.readObject();

            // Print received weather reports
            System.out.println("Weather Reports for " + city + ":");
            for (WeatherReport report : reports) {
                System.out.println(report);
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
