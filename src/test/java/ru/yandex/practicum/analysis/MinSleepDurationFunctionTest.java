package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinSleepDurationFunctionTest {

    @Test
    void shouldFindMinDuration() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(100), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(50), SleepQuality.NORMAL));

        MinSleepDurationFunction f = new MinSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(50L, result.getValue());
    }

    @Test
    void shouldReturnDurationWhenSingleSession() {

        List<SleepingSession> sessions = List.of(new SleepingSession(LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(120), SleepQuality.GOOD));

        MinSleepDurationFunction f = new MinSleepDurationFunction();
        SleepAnalysisResult result = f.apply(sessions);

        assertEquals(120L, result.getValue());
    }
}
