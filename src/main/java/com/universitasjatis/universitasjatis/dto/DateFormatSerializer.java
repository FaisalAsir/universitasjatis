package com.universitasjatis.universitasjatis.dto;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatSerializer extends StdDeserializer<LocalDate> {

    public DateFormatSerializer() {
        super(Date.class);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt){
        try {
            String value = p.readValueAs(String.class);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            LocalDate localDateTime = LocalDate.parse(value,formatter);

            return localDateTime;
        } catch (Exception e) {
            throw new HttpMessageNotReadableException("error convert date");
        }
    }

}
