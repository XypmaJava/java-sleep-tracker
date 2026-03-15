package ru.yandex.practicum.io;

import ru.yandex.practicum.model.SleepQuality;
import ru.yandex.practicum.model.SleepingSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Stream;

public class SleepFileReader {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static List<SleepingSession> read(String path) throws IOException {

        try (Stream<String> lines = Files.lines(Path.of(path))) {

            return lines.filter(line -> !line.isBlank()).map(
                    line -> line.split(";")).map(parts ->
                    new SleepingSession(LocalDateTime.parse(parts[0], FORMATTER),
                            LocalDateTime.parse(parts[1], FORMATTER), SleepQuality.valueOf(parts[2]))).toList();
        }
    }
}