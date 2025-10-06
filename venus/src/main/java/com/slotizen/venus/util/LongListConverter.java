package com.slotizen.venus.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Converter
public class LongListConverter implements AttributeConverter<List<Long>, String> {
    
    private static final Logger logger = LoggerFactory.getLogger(LongListConverter.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String convertToDatabaseColumn(List<Long> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return "[]";
        }
        try {
            String json = objectMapper.writeValueAsString(attribute);
            logger.debug("Converting to database: {} -> {}", attribute, json);
            return json;
        } catch (JsonProcessingException e) {
            logger.error("Error converting list to JSON: {}", attribute, e);
            return "[]";
        }
    }
    
    @Override
    public List<Long> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            List<Long> list = objectMapper.readValue(dbData, new TypeReference<List<Long>>() {});
            logger.debug("Converting from database: {} -> {}", dbData, list);
            return list != null ? list : new ArrayList<>();
        } catch (JsonProcessingException e) {
            logger.error("Error converting JSON to list: {}", dbData, e);
            return new ArrayList<>();
        }
    }
}