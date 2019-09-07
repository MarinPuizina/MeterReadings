package com.marin.app.mr.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "reading")
public class MeterEntity implements Serializable {

    private static final long serialVersionUID = 4514267711190289580L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 50)
    private String client;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 4)
    private Integer year;

    @Column(length = 10)
    private String month;

    @Column(length = 10)
    private Integer reading;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
