package com.marin.app.mr.ui.model.response;

import java.util.List;

public class YearlyReadingsResponseModel {

    private Integer year;
    private List<MonthlyReadings> monthlyReadings;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<MonthlyReadings> getMonthlyReadings() {
        return monthlyReadings;
    }

    public void setMonthlyReadings(List<MonthlyReadings> monthlyReadings) {
        this.monthlyReadings = monthlyReadings;
    }

}
