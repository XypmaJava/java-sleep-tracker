
package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.SleepingSession;
import java.util.List;
import java.util.function.Function;

public class AvgSleepDurationFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        double avg = sessions.stream()
                .mapToLong(SleepingSession::getDurationMinutes)
                .average()
                .orElse(0);
        return new SleepAnalysisResult("Средняя продолжительность сна (мин)", avg);
    }
}
