package com.marin.app.mr.shared.Dto;

import java.io.Serializable;

/**
 * Data transfer object, used in DTO Pattern
 */
public class MeterDto implements Serializable {

    private static final long serialVersionUID = -1069481538071683043L;

    private String client;
    private String address;
    private Integer year;
    private String month;
    private Integer reading;


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }

}
