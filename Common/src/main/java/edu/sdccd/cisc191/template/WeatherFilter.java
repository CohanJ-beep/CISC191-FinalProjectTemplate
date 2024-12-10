package edu.sdccd.cisc191.template;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

public class WeatherFilter {

    public static List<WeatherLocation> filterCitiesByWeather(WeatherLocation[] cities, String condition) {
        return Arrays.stream(cities)
                .filter(city -> city.getWeatherCondition().equalsIgnoreCase(condition))
                .collect(Collectors.toList());
    }
}
