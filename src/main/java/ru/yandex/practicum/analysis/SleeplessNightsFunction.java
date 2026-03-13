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

        // Определяем диапазон дат на основе окончаний сессий (утро, когда мы проснулись)
        LocalDate firstMorning = sessions.get(0).getEnd().toLocalDate();
        LocalDate lastMorning = sessions.get(sessions.size() - 1).getEnd().toLocalDate();

        int sleeplessCount = 0;

        // Идем по дням, проверяя каждую "ночь" перед этим утром
        for (LocalDate date = firstMorning; !date.isAfter(lastMorning); date = date.plusDays(1)) {
            // Ночь — это интервал с 22:00 вчера до 06:00 сегодня
            LocalDateTime nightStart = date.minusDays(1).atTime(22, 0);
            LocalDateTime nightEnd = date.atTime(6, 0);

            boolean sleptDuringNight = sessions.stream().anyMatch(s -> s.getStart().isBefore(nightEnd) && s.getEnd().isAfter(nightStart));

            if (!sleptDuringNight) {
                sleeplessCount++;
            }
        }

        return new SleepAnalysisResult("Количество бессонных ночей", sleeplessCount);
    }
}