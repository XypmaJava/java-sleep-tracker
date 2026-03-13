
package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.SleepingSession;
import java.util.List;
import java.util.function.Function;

public class SleepSessionCounter implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult("Количество сессий сна", sessions.stream().count());
    }
}
