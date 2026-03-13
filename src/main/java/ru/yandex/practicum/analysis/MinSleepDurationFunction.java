
package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.SleepingSession;
import java.util.List;
import java.util.function.Function;

public class MinSleepDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long min = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .min()
                .orElse(0);
        return new SleepAnalysisResult("Минимальная продолжительность сна (мин)", min);
    }
}
