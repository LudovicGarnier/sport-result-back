package com.sportresult.app_back.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Converter
public class UniqueStringListConverter implements AttributeConverter<Set<String>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Set<String> attribute) {
        log.info("convertToDatabaseColumn : {}", attribute);
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur conversion JSON", e);
        }
    }

    @Override
    public Set<String> convertToEntityAttribute(String dbData) {
        log.info("convertToEntityAttribute: {}", dbData);
        if (dbData == null || dbData.trim().isEmpty()) {
            return new HashSet<>();
        }
        try {
            return objectMapper.readValue(dbData,
                    objectMapper.getTypeFactory().constructCollectionType(Set.class, String.class));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Erreur conversion JSON", e);
        }
    }
}
