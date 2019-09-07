package com.marin.app.mr.shared;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.exception.MeterServiceException;
import com.marin.app.mr.shared.Dto.MeterDto;
import com.marin.app.mr.ui.model.response.Error.ErrorMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class ClientAddressValidationTest {

    @Autowired
    ClientAddressValidation clientAddressValidation;

    @Test
    @DisplayName("Test exceptionShouldBeThrownIfNotAtAddress")
    void exceptionShouldBeThrownIfNotAtAddress() {

        MeterDto meterDto = new MeterDto();
        meterDto.setAddress("Bla");

        assertThrows(MeterServiceException.class, () ->
                clientAddressValidation.ifNotAtAddressThrowException(meterDto, "Ulica 7", ErrorMessages.CLIENTS_INVALID_ADDRESS));

    }

    @Test
    @DisplayName("Test exceptionShouldBeThrownIfReadingExists")
    void exceptionShouldBeThrownIfReadingExists() {

        assertThrows(MeterServiceException.class, () ->
                clientAddressValidation.ifReadingThrowException(new MeterEntity(), ErrorMessages.READING_ALREADY_EXISTS));

    }

}