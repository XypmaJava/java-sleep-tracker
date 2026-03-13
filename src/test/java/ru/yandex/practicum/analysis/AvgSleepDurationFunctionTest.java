package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AvgSleepDurationFunctionTest {

    @Test
    void shouldCalculateAverageDuration() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(60), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(120), SleepQuality.NORMAL)
        );

        AvgSleepDurationFunction f = new AvgSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(90.0, result.getValue());
    }

    @Test
    void shouldReturnSameValueForSingleSession() {

        List<SleepingSession> sessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(75), SleepQuality.GOOD)
        );

        AvgSleepDurationFunction f = new AvgSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(75.0, result.getValue());
    }
}