package com.github.pyrinoff.utils;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CsvUtil {

    static List<Map<String, String>> parseCsv(String data, char columnSeparator, String nullValue) throws IOException {
        final CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader().withColumnSeparator(columnSeparator).withNullValue(nullValue);
        MappingIterator<Map<String, String>> it = csvMapper
                .readerFor(Map.class)
                .with(csvSchema)
                .readValues(data);
        return it.readAll();
    }

    static List<Map<String, String>> parseCsv(File file, char columnSeparator, String nullValue) throws IOException {
        final CsvMapper csvMapper = new CsvMapper();
        CsvSchema csvSchema = csvMapper.typedSchemaFor(Map.class).withHeader().withColumnSeparator(columnSeparator).withNullValue(nullValue);
        MappingIterator<Map<String, String>> it = csvMapper
                .readerFor(Map.class)
                .with(csvSchema)
                .readValues(file);
        return it.readAll();
    }

}
