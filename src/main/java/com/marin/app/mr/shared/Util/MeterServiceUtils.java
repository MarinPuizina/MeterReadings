package com.marin.app.mr.shared.Util;

import com.marin.app.mr.data.entity.MeterEntity;
import com.marin.app.mr.shared.Dto.MeterDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeterServiceUtils {

    /**
     * Returns list of MeterDto objects.
     *
     * @param meterEntities   List of MeterEntity objects.
     * @return  List of MeterDto objects.
     */
    public List<MeterDto> mapMeterEntityToMeterDto(List<MeterEntity> meterEntities) {

        ModelMapper modelMapper = getModelMapper();

        List<MeterDto> returnValue = new ArrayList<>();

        for (MeterEntity meterEntity : meterEntities) {

            returnValue.add(modelMapper.map(meterEntity, MeterDto.class));

        }

        return returnValue;
    }

    /**
     * Returns MeterDto.
     *
     * @param meterEntity   MeterEntity object.
     * @return  MeterDto.
     */
    public MeterDto mapMeterEntityToMeterDto(MeterEntity meterEntity) {

        ModelMapper modelMapper = getModelMapper();

        return modelMapper.map(meterEntity, MeterDto.class);
    }

    /**
     * Returns MeterEntity.
     *
     * @param meterDto   MeterDto object.
     * @return  MeterEntity.
     */
    public MeterEntity mapMeterDtoToMeterEntity(MeterDto meterDto) {

        ModelMapper modelMapper = getModelMapper();

        MeterEntity meterEntity = modelMapper.map(meterDto, MeterEntity.class);

        return meterEntity;
    }


    /**
     * Returns the model mapper.
     * @return  The model mapper.
     */
    public static ModelMapper getModelMapper() {

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }

}
