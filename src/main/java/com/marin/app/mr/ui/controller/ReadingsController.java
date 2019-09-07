package com.marin.app.mr.ui.controller;

import com.marin.app.mr.exception.MeterServiceException;
import com.marin.app.mr.service.MeterService;
import com.marin.app.mr.shared.Dto.MeterDto;
import com.marin.app.mr.shared.Util.MeterServiceUtils;
import com.marin.app.mr.ui.model.request.MeterReadingRequestModel;
import com.marin.app.mr.ui.model.response.AggregateReadingResponseModel;
import com.marin.app.mr.ui.model.response.MonthReadingResponseModel;
import com.marin.app.mr.ui.model.response.MonthlyReadings;
import com.marin.app.mr.ui.model.response.YearlyReadingsResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/readings") //http://localhost:8080/readings
public class ReadingsController {

    @Autowired
    MeterService meterService;

    @GetMapping("/")
    public String status() {

        return "It's working.";
    }

    /**
     * GET http://localhost:8080/readings/addresses/{address}/years/{year}
     *
     * Returns the aggregate meter readings for a year.
     *
     * @param address   The address of the client.
     * @param year  The year used to calculate the aggregate meter reading for that year.
     * @return  The aggregate meter readings for a year.
     */
    @GetMapping(path = "/addresses/{address}/years/{year}")
    public ResponseEntity<AggregateReadingResponseModel> getAggregateReading(
            @PathVariable("address") String address, @PathVariable("year") Integer year) {

        Integer totalValue = meterService.getYearlyAggregateReading(address, year);

        if (totalValue == null || totalValue == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Set values
        AggregateReadingResponseModel returnValue = new AggregateReadingResponseModel();
        returnValue.setYear(year);
        returnValue.setTotal(totalValue);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    /**
     * GET http://localhost:8080/readings/addresses/{address}/years/{year}/months
     *
     * Returns the monthly meter readings per year.
     *
     * @param address   The address of the client.
     * @param year  The year used to get the monthly readings.
     * @return  The monthly meter readings per year.
     */
    @GetMapping(path = "addresses/{address}/years/{year}/months")
    public ResponseEntity<YearlyReadingsResponseModel> getYearlyReadings(
            @PathVariable("address") String address, @PathVariable("year") Integer year) {

        List<MeterDto> meterDtos = meterService.getMonthlyReading(address, year);

        if (meterDtos == null || meterDtos.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // ModelMapper
        ModelMapper modelMapper = MeterServiceUtils.getModelMapper();

        List<MonthlyReadings> monthlyReadings = new ArrayList<>();

        // Mapping MeterDto to MonthlyReadings
        for (MeterDto meterDto : meterDtos) {
            monthlyReadings.add(modelMapper.map(meterDto, MonthlyReadings.class));
        }

        // Set values
        YearlyReadingsResponseModel returnValue = new YearlyReadingsResponseModel();
        returnValue.setYear(year);
        returnValue.setMonthlyReadings(monthlyReadings);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    /**
     * GET http://localhost:8080/readings/addresses/{address}/years/{year}/months/{month}
     *
     * Returns the meter reading per month per year.
     *
     * @param address   The address of the client.
     * @param year  The year used to get the reading of the month.
     * @param month The month used to get the reading for that month.
     * @return  The meter reading per month per year.
     */
    @GetMapping(path = "/addresses/{address}/years/{year}/months/{month}")
    public ResponseEntity<MonthReadingResponseModel> getMonthReading(
            @PathVariable("address") String address,
            @PathVariable("year") Integer year,
            @PathVariable("month") String month) {

        // ModelMapper
        ModelMapper modelMapper = MeterServiceUtils.getModelMapper();

        MeterDto meterDto = meterService.getMonthReading(address, year, month);

        if (meterDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        MonthReadingResponseModel returnValue = modelMapper.map(meterDto, MonthReadingResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }

    /**
     * POST http://localhost:8080/readings
     *
     * Stores meter reading in database.
     *
     * @param meterReading  Representation of client and meter data.
     * @throws MeterServiceException
     */
    @PostMapping()
    public void createReading(@RequestBody MeterReadingRequestModel meterReading) {

        // ModelMapper
        ModelMapper modelMapper = MeterServiceUtils.getModelMapper();

        MeterDto meterDto = modelMapper.map(meterReading, MeterDto.class);

        meterService.createReading(meterDto);

    }

}
