package com.marin.app.mr.service;

import com.marin.app.mr.shared.Dto.MeterDto;

import java.util.List;

public interface MeterService {

    /**
     * Returns the aggregated reading.
     *
     * @param address   The address of the client
     * @param year  The year used to get the monthly readings.
     * @return  The aggregated reading.
     */
    Integer getYearlyAggregateReading(String address, Integer year);

    /**
     * Returns the list of all monthly readings.
     *
     * @param address   The address of the client.
     * @param year  The year used to get the monthly readings.
     * @return  The list of all monthy readings.
     */
    List<MeterDto> getMonthlyReading(String address, Integer year);

    /**
     * Returns the year, the month and the reading value.
     *
     * @param address   The address of the client.
     * @param year  The year used to get the monthly readings.
     * @return  The year, the month and the reading value.
     */
    MeterDto getMonthReading(String address, Integer year, String month);

    /**
     * Returns the MeterDto.
     *
     * @param meterDto  MeterDto object of the request model.
     * @return  The MeterDto.
     */
    MeterDto createReading(MeterDto meterDto);

}
