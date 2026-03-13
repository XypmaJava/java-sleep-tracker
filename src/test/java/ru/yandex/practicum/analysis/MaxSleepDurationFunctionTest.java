package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaxSleepDurationFunctionTest {

    @Test
    void shouldFindMaxDuration() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(80), SleepQuality.GOOD),

                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(200), SleepQuality.NORMAL));

        MaxSleepDurationFunction f = new MaxSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(200L, result.getValue());
    }

    @Test
    void shouldReturnDurationWhenSingleSession() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(90), SleepQuality.GOOD));

        MaxSleepDurationFunction f = new MaxSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(90L, result.getValue());
    }
}