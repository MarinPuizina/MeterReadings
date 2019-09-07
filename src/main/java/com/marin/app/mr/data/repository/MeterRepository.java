package com.marin.app.mr.data.repository;

import com.marin.app.mr.data.entity.MeterEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MeterRepository extends CrudRepository<MeterEntity, Long> {

    /**
     * Returns the sum of reading column for specific address and year.
     *
     * @param address   The address column in the database.
     * @param year  The year column in the database.
     * @return  The sum of reading column for specific address and year.
     */
    @Query(value="select sum(reading) from reading r where r.address = :address and r.year = :year", nativeQuery = true)
    Optional<Integer> findYearlyAggregateReading(@Param("address") String address, @Param("year") Integer year);

    /**
     * Returns the month and the reading column values for specific address and year.
     *
     * @param address   The address column in the database.
     * @param year  The year column in the database.
     * @return  The month and the reading column values for specific address and year.
     */
    List<MeterEntity> findMonthAndReadingByAddressAndYear(String address, Integer year);

    /**
     * Returns the year, the month and the reading value for specific address, year and month.
     *
     * @param address   The address column in the database.
     * @param year  The year column in the database.
     * @param month  The month column in the database.
     * @return  The year, the month and the reading value for specific address, year and month.
     */
    MeterEntity findYearAndMonthAndReadingByAddressAndYearAndMonth(String address, Integer year, String month);

    /**
     * Returns the address for specific client.
     *
     * @param client   The client column in the database.
     * @return  The address for specific client.
     */
    @Query(value="select distinct address from reading r where r.client = :client", nativeQuery = true)
    Optional<String> findAddressByClient(String client);

    /**
     * Returns the reading value for specific address, year and month.
     *
     * @param address   The address column in the database.
     * @param year  The year column in the database.
     * @param month  The month column in the database.
     * @return  The reading value for specific address, year and month.
     */
    MeterEntity findReadingByAddressAndYearAndMonth(String address, Integer year, String month);

}
