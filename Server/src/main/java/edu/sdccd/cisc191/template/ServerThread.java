package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ServerThread extends Thread {

    private Socket socket;
    private WeatherLocation[] cities;
    private WeatherReport[][] weatherReports;

    public ServerThread(Socket socket, WeatherLocation[] cities, WeatherReport[][] weatherReports) {
        this.socket = socket;
        this.cities = cities;
        this.weatherReports = weatherReports;
    }

    @Override
    public void run() {
        try (ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

            // Client requests location
            String requestedCity = (String) in.readObject();

            // Matches to WeatherLocation
            for (int i = 0; i < cities.length; i++) {
                if (cities[i].getName().equals(requestedCity)) {
                    // Send weather reports back
                    out.writeObject(weatherReports[i]);
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
