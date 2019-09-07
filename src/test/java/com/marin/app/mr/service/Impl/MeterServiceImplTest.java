package com.marin.app.mr.service.Impl;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.data.repository.MeterRepository;
import com.marin.app.mr.shared.ClientAddressValidation;
import com.marin.app.mr.shared.Dto.MeterDto;
import com.marin.app.mr.shared.Util.MeterServiceImpl;
import com.marin.app.mr.shared.Util.MeterServiceUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class MeterServiceImplTest {

    @InjectMocks
    MeterServiceImpl meterService;

    @Mock
    MeterRepository meterRepository;

    @Mock
    ClientAddressValidation clientAddressValidation;

    @Mock
    MeterServiceUtils meterServiceUtils;

    MeterEntity meterEntity;
    MeterDto meterDto;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        meterEntity = new MeterEntity();
        meterEntity.setClient("Marin Puizina");
        meterEntity.setAddress("Ulica 7");
        meterEntity.setYear(2019);
        meterEntity.setMonth("September");
        meterEntity.setReading(17);

        meterDto = new MeterDto();
        meterDto.setClient("Marin Puizina");
        meterDto.setAddress("Ulica 7");
        meterDto.setYear(2019);
        meterDto.setMonth("September");
        meterDto.setReading(17);

    }

    @Test
    @DisplayName("Test findYearlyAggregateReading - Found")
    void testFindYearlyAggregateReadingFound() {

        Integer mockValue = 44;
        doReturn(Optional.of(mockValue)).when(meterRepository).findYearlyAggregateReading("Ulica 7", 2019);

        Optional<Integer> returnedValue = meterRepository.findYearlyAggregateReading("Ulica 7", 2019);

        assertTrue(returnedValue.isPresent(), "Value was not found");
        assertEquals(mockValue, returnedValue.get(), "Value should be 44");

    }

    @Test
    @DisplayName("Test findYearlyAggregateReading - Not Found")
    void testFindYearlyAggregateReadingNotFound() {

        doReturn(Optional.empty()).when(meterRepository).findYearlyAggregateReading("Ulica 7", 2019);

        Optional<Integer> returnedValue = meterRepository.findYearlyAggregateReading("Ulica 7", 2019);

        assertFalse(returnedValue.isPresent(), "Value was found, when it shouldn't be");

    }

    @Test
    @DisplayName("Test findAddressByClient - Found")
    void testFindAddressByClientFound() {

        doReturn(Optional.of("Ulica 7")).when(meterRepository).findAddressByClient("Marin Puizina");

        Optional<String> returnedValue = meterRepository.findAddressByClient("Marin Puizina");

        assertTrue(returnedValue.isPresent(), "Value was not found");
        assertEquals( "Ulica 7", returnedValue.get(), "Value should be Ulica 7");

    }

    @Test
    @DisplayName("Test findAddressByClient - Not Found")
    void testFindAddressByClientNotFound() {

        doReturn(Optional.empty()).when(meterRepository).findAddressByClient("Marin Puizina");

        Optional<String> returnedValue = meterRepository.findAddressByClient("Marin Puizina");

        assertFalse(returnedValue.isPresent(), "Value was found, when it shouldn't be");

    }

    @Test
    @DisplayName("Test createReading - Success")
    void testCreateReading() {

        when(meterRepository.findAddressByClient(anyString())).thenReturn(Optional.of("Ulica 7"));
        clientAddressValidation.validateClientAddress(meterDto, "Ulica 7", meterRepository);
        when(meterRepository.save(any())).thenReturn(meterEntity);
        when(meterServiceUtils.mapMeterEntityToMeterDto(meterEntity)).thenReturn(meterDto);

        MeterDto returnedValue = meterService.createReading(meterDto);

        assertNotNull(returnedValue);
        assertEquals("Marin Puizina", returnedValue.getClient());

    }

}