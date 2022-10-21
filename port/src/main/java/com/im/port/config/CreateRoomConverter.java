package com.im.port.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.port.vo.dto.CreateRoomDto;

import lombok.SneakyThrows;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CreateRoomConverter implements Converter<String, CreateRoomDto> {

    private ObjectMapper objectMapper;
    public CreateRoomConverter(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    @Override
    public CreateRoomDto convert(String value) {
        return objectMapper.readValue(value, new TypeReference<>() {});
    }
}
