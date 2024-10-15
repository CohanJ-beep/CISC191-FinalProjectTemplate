package edu.sdccd.cisc191.template;

import java.io.Serializable;
import java.util.Date;

public class WeatherReport implements Serializable {

    private Date reportDate;
    private String reportDetails;

    public WeatherReport(Date reportDate, String reportDetails) {
        this.reportDate = reportDate;
        this.reportDetails = reportDetails;
    }

    /**
     * @return the response date
     */
    public Date getReportDate() {
        return reportDate;
    }

    /**
     * @return the report details
     */
    public String getReportDetails() {
        return reportDetails;
    }

    @Override
    public String toString() {
        return "Weather Report on " + reportDate + ": " + reportDetails;
    }
}

