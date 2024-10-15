package edu.sdccd.cisc191.template;

import java.io.Serializable;

// Child class
public class WeatherLocation extends Location implements Serializable {

    private String weatherCondition;

    /**
     * @param name location name
     * @param weatherCondition weather statement
     */
    public WeatherLocation(String name, String weatherCondition) {
        super(name);
        this.weatherCondition = weatherCondition;
    }

    /**
     * @return the weather condition
     */
    public String getWeatherCondition() {
        return weatherCondition;
    }

    @Override
    public String toString() {
        return getName() + ": " + weatherCondition;
    }
}
