package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.SleepingSession;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsFunction implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        LocalDate firstMorning = sessions.get(0).getEnd().toLocalDate();
        LocalDate lastMorning = sessions.get(sessions.size() - 1).getEnd().toLocalDate();

        long sleeplessCount = firstMorning.datesUntil(lastMorning.plusDays(1)) // поток дат
                .filter(date -> {
                    LocalDateTime nightStart = date.minusDays(1).atTime(22, 0);
                    LocalDateTime nightEnd = date.atTime(6, 0);

                    boolean sleptDuringNight = sessions.stream().anyMatch(
                            s -> s.getStart().isBefore(nightEnd) && s.getEnd().isAfter(nightStart));

                    return !sleptDuringNight;
                }).count();

        return new SleepAnalysisResult("Количество бессонных ночей", (int) sleeplessCount);
    }
}