package com.github.pyrinoff.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.jetbrains.annotations.NotNull;

public interface GetByUrlUtilJsonUtil {

    static String objectToJson(Object object, boolean pretty) throws JsonProcessingException {
        @NotNull final ObjectMapper objectMapper = new ObjectMapper();
        //objectMapper.registerModule(new JavaTimeModule());
        ObjectWriter objectWriter = pretty ? objectMapper.writerWithDefaultPrettyPrinter() : objectMapper.writer();
        return objectWriter.writeValueAsString(object);
    }

    static <T> T jsonToObject(String jsonString, Class<T> clazz) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, clazz);
    }

}
