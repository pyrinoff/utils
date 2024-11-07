package com.github.pyrinoff.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public interface FileUtil {

    static void filePutContent(String filename, List<String> content) throws IOException {
        //if(content.isEmpty() || content.get(0).isEmpty()) return;
        Files.write(Path.of(filename), content);
    }

    @Nullable
    static List<String> fileGetContent(String filename) throws IOException {
        try {
            return Files.readAllLines(Path.of(filename));
        } catch (@NotNull final NoSuchFileException e) {
            return null;
        }
    }

    @Nullable
    static String fileGetContentAsString(String filename) throws IOException {
        try {
            return Files.readString(Path.of(filename));
        } catch (@NotNull final NoSuchFileException e) {
            return null;
        }
    }

    static String getFilenameFromPath(@NotNull final String filepath) {
        @NotNull final Path path = Path.of(filepath);
        return path.getFileName().toString();
    }

    static String getFileExtensionFileSystem(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex != -1 && lastDotIndex < fileName.length() - 1) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    static void deleteFilesInFolder(String folderPath, boolean failOnError) throws IOException {
        Path directory = Paths.get(folderPath);
        if (!Files.isDirectory(directory)) return;
        try (Stream<Path> listOfFiles = Files.list(directory)) {
            listOfFiles.filter(Files::isRegularFile).forEach(path -> {
                try {
                    Files.delete(path);
                } catch (IOException e) {
                    System.err.println("Failed to delete file: " + path);
                    if (failOnError) throw new RuntimeException(e);
                }
            });
        }
    }

    static void deleteFilesFromDisc(List<String> filepathsOfSavedPhotos, boolean failOnError) {
        for (String oneFile : filepathsOfSavedPhotos) {
            try {
                Files.deleteIfExists(Path.of(oneFile));
            } catch (IOException e) {
                System.err.println("Failed to delete file: " + oneFile);
                if (failOnError) throw new RuntimeException(e);
            }
        }
    }

    static Path getFilepathFromResources(Class<?> clazz, String filepathInResources) {
        return new File(clazz
                .getClassLoader()
                .getResource(filepathInResources)
                .getFile()).toPath();
    }

}
