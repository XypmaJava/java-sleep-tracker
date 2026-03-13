package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.SleepingSession;
import ru.yandex.practicum.model.SleepQuality;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SleeplessNightsFunctionTest {

    private final SleeplessNightsFunction function = new SleeplessNightsFunction();

    @Test
    void shouldDetectNightSleepAcrossMidnight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025,10,1,23,30),
                        LocalDateTime.of(2025,10,2,6,0),
                        SleepQuality.GOOD)
        );
        assertEquals(0, function.apply(sessions).getValue());
    }

    @Test
    void shouldDetectNightSleepAfterMidnight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025,10,2,1,0),
                        LocalDateTime.of(2025,10,2,5,0),
                        SleepQuality.NORMAL)
        );
        assertEquals(0, function.apply(sessions).getValue());
    }

    @Test
    void shouldCountSleeplessNightWhenSleepOnlyDaytime() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025,10,2,14,0),
                        LocalDateTime.of(2025,10,2,18,0),
                        SleepQuality.NORMAL)
        );
        assertEquals(1, function.apply(sessions).getValue());
    }

    @Test
    void shouldHandleSessionStartingAtMidnight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.of(2025, 10, 1, 0, 0),
                        LocalDateTime.of(2025, 10, 1, 6, 30),
                        SleepQuality.GOOD)
        );
        assertEquals(0, function.apply(sessions).getValue());
    }
}