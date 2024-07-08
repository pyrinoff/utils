package com.github.pyrinoff.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public interface DumpYamlUtil {

    static @Nullable <T> T get(@NotNull final String filepath, @NotNull final Class<T> clazz) throws IOException {
        if(!Files.exists(Path.of(filepath))) return null;
        final byte[] bytes = Files.readAllBytes(Paths.get(filepath));
        @NotNull final String yaml = new String(bytes);
        @NotNull final ObjectMapper objectMapper = new YAMLMapper();
        return objectMapper.readValue(yaml, clazz);
    }

    static void save(@NotNull final String filepath, @NotNull final Object object) throws IOException {
        @NotNull final File file = new File(filepath);
        @NotNull final Path path = file.toPath();
        Files.deleteIfExists(path);
        Files.createFile(path);
        try(@NotNull final FileOutputStream fileOutputStream = new FileOutputStream(file);) {
            @NotNull final ObjectMapper objectMapper = new YAMLMapper();
            @NotNull final String yaml = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            fileOutputStream.write(yaml.getBytes());
            fileOutputStream.flush();
        }
    }

}
