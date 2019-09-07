package com.marin.app.mr.shared.Util;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.shared.Dto.MeterDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MeterServiceUtilsTest {

    @Autowired
    MeterServiceUtils meterServiceUtils;

    MeterEntity meterEntity;
    MeterDto meterDto;

    @Test
    @DisplayName("TEST meterDtoShouldHaveMeterEntityFieldValues")
    void meterDtoShouldHaveMeterEntityFieldValues() {

        mockMeterEntity();
        mockMeterDto();

        Assertions.assertThat(meterServiceUtils.mapMeterEntityToMeterDto(meterEntity)).isEqualToComparingFieldByField(meterDto);

    }

    @Test
    @DisplayName("TEST meterEntityShouldHaveMeterDtoFieldValues")
    void meterEntityShouldHaveMeterDtoFieldValues() {

        mockMeterEntity();
        mockMeterDto();

        Assertions.assertThat(meterServiceUtils.mapMeterDtoToMeterEntity(meterDto) ).isEqualToComparingFieldByField(meterEntity);

    }

    private void mockMeterEntity() {

        meterEntity = new MeterEntity();
        meterEntity.setClient("Marin Puizina");
        meterEntity.setAddress("Ulica 7");
        meterEntity.setYear(2019);
        meterEntity.setMonth("September");
        meterEntity.setReading(17);

    }

    private void mockMeterDto() {

        meterDto = new MeterDto();
        meterDto.setClient("Marin Puizina");
        meterDto.setAddress("Ulica 7");
        meterDto.setYear(2019);
        meterDto.setMonth("September");
        meterDto.setReading(17);
    }

}