package com.marin.app.mr.shared.Util;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.data.repository.MeterRepository;
import com.marin.app.mr.exception.MeterServiceException;
import com.marin.app.mr.service.MeterService;
import com.marin.app.mr.shared.ClientAddressValidation;
import com.marin.app.mr.shared.Dto.MeterDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MeterServiceImpl implements MeterService {

    MeterRepository meterRepository;
    MeterServiceUtils meterServiceUtils;
    ClientAddressValidation clientAddressValidation;

    @Autowired
    public MeterServiceImpl(MeterRepository meterRepository,
                            MeterServiceUtils meterServiceUtils,
                            ClientAddressValidation clientAddressValidation) {

        this.meterRepository = meterRepository;
        this.meterServiceUtils = meterServiceUtils;
        this.clientAddressValidation = clientAddressValidation;

    }

    @Override
    public Integer getYearlyAggregateReading(String address, Integer year) {

        Optional<Integer> returnValue = meterRepository.findYearlyAggregateReading(address, year);

        return returnValue.orElse(0);
    }

    @Override
    public List<MeterDto> getMonthlyReading(String address, Integer year) {

        List<MeterEntity> monthlyReadings = meterRepository.findMonthAndReadingByAddressAndYear(address, year);

        List<MeterDto> returnValue = meterServiceUtils.mapMeterEntityToMeterDto(monthlyReadings);

        return returnValue;
    }

    @Override
    public MeterDto getMonthReading(String address, Integer year, String month) {

        MeterEntity meterEntity = meterRepository.findYearAndMonthAndReadingByAddressAndYearAndMonth(address, year, month);

        if (meterEntity == null) {
            return null;
        }

        MeterDto returnValue = meterServiceUtils.mapMeterEntityToMeterDto(meterEntity);

        return returnValue;
    }

    @Override
    public MeterDto createReading(MeterDto meterDto) throws MeterServiceException {

        // Check if the client is already in the db
        String storedAddress = meterRepository.findAddressByClient(meterDto.getClient()).orElse("");

        // Check if he is at the provided address
        clientAddressValidation.validateClientAddress(meterDto, storedAddress, meterRepository);

        // If the client is not in database or if there is not a reading, then store the data
        MeterEntity storedData = meterRepository.save(meterServiceUtils.mapMeterDtoToMeterEntity(meterDto));

        MeterDto returnValue = meterServiceUtils.mapMeterEntityToMeterDto(storedData);

        return returnValue;
    }

}
