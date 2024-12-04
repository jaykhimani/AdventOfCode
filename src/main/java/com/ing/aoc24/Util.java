package com.ing.aoc24;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Util {

    public static String getInput(String dayNumber) {
        try {
            return Files.readString(getPath(dayNumber));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static Stream<String> getInputStream(String dayNumber) {
        try {
            return Files.lines(getPath(dayNumber));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getLines(String dayNumber) {
        try {
            return Files.readAllLines(getPath(dayNumber));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path getPath(String dayNumber) throws URISyntaxException {
        return Paths.get(Util.class.getClassLoader().getResource("input-day" + dayNumber + ".txt").toURI());
    }
}
