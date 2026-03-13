package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BadQualitySleepCounterTest {

    @Test
    void shouldCountBadQualitySessions() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(),
                        LocalDateTime.now().plusHours(7),
                        SleepQuality.BAD),

                new SleepingSession(LocalDateTime.now(),
                        LocalDateTime.now().plusHours(6),
                        SleepQuality.GOOD)
        );

        BadQualitySleepCounter counter = new BadQualitySleepCounter();

        SleepAnalysisResult result = counter.apply(sessions);

        assertEquals(1L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenNoBadSessions() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(),
                        LocalDateTime.now().plusHours(7),
                        SleepQuality.GOOD)
        );

        BadQualitySleepCounter counter = new BadQualitySleepCounter();

        SleepAnalysisResult result = counter.apply(sessions);

        assertEquals(0L, result.getValue());
    }
}