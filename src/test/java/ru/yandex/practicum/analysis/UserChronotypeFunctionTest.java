package ru.yandex.practicum.analysis;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.model.Chronotype;
import ru.yandex.practicum.model.SleepQuality;
import ru.yandex.practicum.model.SleepingSession;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserChronotypeFunctionTest {

    private final UserChronotypeFunction function = new UserChronotypeFunction();

    @Test
    void shouldClassifyOwl() {
        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 23, 30),
                LocalDateTime.of(2025, 10, 2, 9, 30),
                SleepQuality.GOOD
        );
        Chronotype result = function.apply(List.of(night1));
        assertEquals(Chronotype.OWL, result);
    }

    @Test
    void shouldClassifyLark() {
        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 21, 0),
                LocalDateTime.of(2025, 10, 2, 6, 30),
                SleepQuality.NORMAL
        );
        Chronotype result = function.apply(List.of(night1));
        assertEquals(Chronotype.LARK, result);
    }

    @Test
    void shouldClassifyDoveWhenMixedOrTie() {
        SleepingSession night1 = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 22, 30),
                LocalDateTime.of(2025, 10, 2, 8, 0),
                SleepQuality.NORMAL
        );
        SleepingSession night2 = new SleepingSession(
                LocalDateTime.of(2025, 10, 2, 23, 15),
                LocalDateTime.of(2025, 10, 3, 9, 0),
                SleepQuality.GOOD
        );
        Chronotype result = function.apply(List.of(night1, night2));
        assertEquals(Chronotype.PIGEON, result);
    }

    @Test
    void shouldReturnDoveWhenNoNightSleep() {
        SleepingSession dayNap = new SleepingSession(
                LocalDateTime.of(2025, 10, 1, 14, 0),
                LocalDateTime.of(2025, 10, 1, 15, 0),
                SleepQuality.NORMAL
        );
        Chronotype result = function.apply(List.of(dayNap));
        assertEquals(Chronotype.PIGEON, result);
    }
}