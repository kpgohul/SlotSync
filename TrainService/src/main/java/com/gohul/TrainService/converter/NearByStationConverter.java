package com.gohul.TrainService.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gohul.TrainService.entity.NearbyStation;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Converter
@RequiredArgsConstructor
public class NearByStationConverter implements AttributeConverter<List<NearbyStation>, String> {

    private final ObjectMapper objectMapper;

    @Override
    public String convertToDatabaseColumn(List<NearbyStation> list) {
        try{
            return objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Could not convert nearbyStation as String");
        }
    }

    @Override
    public List<NearbyStation> convertToEntityAttribute(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<NearbyStation>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
