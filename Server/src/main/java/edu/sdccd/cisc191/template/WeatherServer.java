package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class WeatherServer {

    private static WeatherLocation[] cities = new WeatherLocation[2];
    private static WeatherReport[][] weatherReports = new WeatherReport[2][2];
    private static final String DATA_FILE = "weather_data.ser";

    public static void main(String[] args) {
        loadData();

        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server running at port 12345");

            while (true) {
                Socket socket = serverSocket.accept();
                // Create a new thread for each client connection
                new ServerThread(socket, cities, weatherReports).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            cities = (WeatherLocation[]) in.readObject();
            weatherReports = (WeatherReport[][]) in.readObject();
            System.out.println("Data loaded from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No data found, using default values.");
            populateWeatherLocations();
            loadWeatherReports();
        }
    }

    private static void populateWeatherLocations() {
        cities[0] = new WeatherLocation("San Diego", "Sunny");
        cities[1] = new WeatherLocation("New York", "Rainy");
    }

    private static void loadWeatherReports() {
        weatherReports[0][0] = new WeatherReport(new Date(), "75F, clear skies, no precipitation");
        weatherReports[0][1] = new WeatherReport(new Date(), "70F, light breeze, no precipitation");

        weatherReports[1][0] = new WeatherReport(new Date(), "60F, overcast, moderate rain");
        weatherReports[1][1] = new WeatherReport(new Date(), "58F, windy, heavy rain");
    }
}

