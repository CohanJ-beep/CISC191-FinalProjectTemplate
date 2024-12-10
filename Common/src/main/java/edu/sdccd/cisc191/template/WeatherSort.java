package edu.sdccd.cisc191.template;

import java.util.Arrays;
import java.util.Comparator;

public class WeatherSort {

    public static void sortReportsByDate(WeatherReport[][] reports) {
        for (int i = 0; i < reports.length; i++) {
            Arrays.sort(reports[i], Comparator.comparing(WeatherReport::getReportDate));
        }
    }
}

