package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.Date;

public class CustomerResponse implements Serializable {

    private int index;
    private String requestedCity;
    private static WeatherLocation[] cities = new WeatherLocation[2]; // One-dimensional array of cities
    private static WeatherReport[][] weatherReports = new WeatherReport[2][2]; // Two-dimensional array of weather reports

    /**
     * @param requestedCity the city written in the terminal
     */
    public CustomerResponse(String requestedCity){
        this.requestedCity = requestedCity;
    }

    /**
     * Sets index to the row number which corresponds with the city requested by client
     */
    public void findIndex(){
        populateWeatherLocations();
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].getName().equals(requestedCity)) {
                // Send weather reports back
                index = i;
                break;
            }
        }
    }

    /**
     * @return the index value
     */
    public int getIndex(){
        return index;
    }

    /**
     * populates the 1d array called cities with instances of the WeatherLocation class
     */
    public static void populateWeatherLocations() {
        cities[0] = new WeatherLocation("San Diego", "Sunny");
        cities[1] = new WeatherLocation("New York", "Rainy");
    }

    /**
     * populates the 2d array called weatherReports with instances of the WeatherReport class
     * the rows of the 2d array represent the cities
     * the cols of the 2d array represent the weather forecasts
     */
    public static void loadWeatherReports() {
        weatherReports[0][0] = new WeatherReport(new Date(), "75F, clear skies, no precipitation");
        weatherReports[0][1] = new WeatherReport(new Date(), "70F, light breeze, no precipitation");

        weatherReports[1][0] = new WeatherReport(new Date(), "60F, overcast, moderate rain");
        weatherReports[1][1] = new WeatherReport(new Date(), "58F, windy, heavy rain");
    }

    public WeatherLocation[] getCities(){
        return cities;
    }

    public WeatherReport[][] getWeatherReports(){
        return weatherReports;
    }

}

