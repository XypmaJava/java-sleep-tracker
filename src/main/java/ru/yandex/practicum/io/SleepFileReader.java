package ru.yandex.practicum.io;

import ru.yandex.practicum.model.SleepQuality;
import ru.yandex.practicum.model.SleepingSession;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SleepFileReader {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    public static List<SleepingSession> read(String path) throws IOException {

        return Files.lines(Path.of(path)).map(line -> line.split(";")).map(parts ->
                new SleepingSession(LocalDateTime.parse(parts[0], FORMAT), LocalDateTime.parse(parts[1], FORMAT),
                        SleepQuality.valueOf(parts[2]))).toList();
    }
}
