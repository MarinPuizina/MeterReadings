package com.marin.app.mr.shared;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.data.repository.MeterRepository;
import com.marin.app.mr.exception.MeterServiceException;
import com.marin.app.mr.shared.Dto.MeterDto;
import com.marin.app.mr.ui.model.response.Error.ErrorMessages;
import org.springframework.stereotype.Service;

@Service
public class ClientAddressValidation {

    /**
     * Method used to throw exceptions for specific cases.
     */
    public void validateClientAddress(MeterDto meterDto, String storedAddress, MeterRepository meterRepository) {

        // If he is not at the provided address throw an exception
        ifNotAtAddressThrowException(meterDto, storedAddress, ErrorMessages.CLIENTS_INVALID_ADDRESS);

        // If he is at the provided address
        // Check if there is a reading for provided address, year, month
        if(storedAddress.contains(meterDto.getAddress())) {

            MeterEntity meterEntity = meterRepository.findReadingByAddressAndYearAndMonth(
                    meterDto.getAddress(), meterDto.getYear(), meterDto.getMonth());

            // If there is a reading, then throw an exception
            ifReadingThrowException(meterEntity, ErrorMessages.READING_ALREADY_EXISTS);

        }

    }

    /**
     * Method used for throwing custom exception when addresses are not equal or not empty.
     *
     * @param meterDto  Used to get address value.
     * @param storedAddress Used to compare with MeterDto address property.
     * @param errorMessage  Custom error message.
     */
    public void ifNotAtAddressThrowException(MeterDto meterDto, String storedAddress, ErrorMessages errorMessage) {

        if(!storedAddress.isEmpty() && !storedAddress.contains(meterDto.getAddress())){

            throw new MeterServiceException(errorMessage.getErrorMessage());
        }

    }

    /**
     * Method used for throwing custom exception when MeterEntity is not null.
     *
     * @param meterEntity   Used to check if it's null.
     * @param errorMessage  Custom error message.
     */
    public void ifReadingThrowException(MeterEntity meterEntity, ErrorMessages errorMessage) {

        if(meterEntity != null) {

            throw new MeterServiceException(errorMessage.getErrorMessage());
        }

    }

}
