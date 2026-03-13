package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.SleepQuality;
import ru.yandex.practicum.model.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepSessionCounterTest {

    @Test
    void shouldCountSessions() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(7), SleepQuality.GOOD), new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusHours(6), SleepQuality.NORMAL));

        SleepSessionCounter counter = new SleepSessionCounter();
        SleepAnalysisResult result = counter.apply(sessions);

        assertEquals(2L, result.getValue());
    }

    @Test
    void shouldReturnZeroWhenNoSessions() {

        List<SleepingSession> sessions = List.of();

        SleepSessionCounter counter = new SleepSessionCounter();

        SleepAnalysisResult result = counter.apply(sessions);

        assertEquals(0L, result.getValue());
    }
}
