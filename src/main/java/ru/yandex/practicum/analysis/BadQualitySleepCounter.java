
package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.SleepQuality;
import ru.yandex.practicum.model.SleepingSession;
import java.util.List;
import java.util.function.Function;

public class BadQualitySleepCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long count = sessions.stream()
                .filter(s -> s.getQuality() == SleepQuality.BAD)
                .count();
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", count);
    }
}
