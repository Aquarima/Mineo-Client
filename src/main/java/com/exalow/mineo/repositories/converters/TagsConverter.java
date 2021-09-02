package com.exalow.mineo.repositories.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class TagsConverter implements AttributeConverter<List<String>, String> {

    @Override
    public String convertToDatabaseColumn(List<String> tags) {
        return String.join(",", tags);
    }

    @Override
    public List<String> convertToEntityAttribute(String data) {
        return (data == null) ? new ArrayList<>() : List.of(data.split(","));
    }
}
