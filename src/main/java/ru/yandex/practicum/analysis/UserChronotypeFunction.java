package ru.yandex.practicum.analysis;

import ru.yandex.practicum.model.Chronotype;
import ru.yandex.practicum.model.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.Map;
import java.util.stream.Collectors;

public class UserChronotypeFunction implements Function<List<SleepingSession>, Chronotype> {

    @Override
    public Chronotype apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) return Chronotype.PIGEON;

        // Отбираем только ночные сессии
        List<SleepingSession> nightSessions = sessions.stream().filter(this::isNightSleep).collect(Collectors.toList());

        if (nightSessions.isEmpty()) return Chronotype.PIGEON;

        // Классификация ночей
        Map<Chronotype, Long> typeCounts = nightSessions.stream().map(this::classifyNight)
                .collect(Collectors.groupingBy(type -> type, Collectors.counting()));

        long owlCount = typeCounts.getOrDefault(Chronotype.OWL, 0L);
        long larkCount = typeCounts.getOrDefault(Chronotype.LARK, 0L);
        long pigeonCount = typeCounts.getOrDefault(Chronotype.PIGEON, 0L);

        if ((owlCount > larkCount) && (owlCount > pigeonCount)) return Chronotype.OWL;
        if ((larkCount > owlCount) && (larkCount > pigeonCount)) return Chronotype.LARK;

        return Chronotype.PIGEON; // ничья или лидер "голубь"
    }

    private boolean isNightSleep(SleepingSession session) {
        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();
        return start.isBefore(LocalTime.of(23, 59)) && end.isAfter(LocalTime.of(6, 0));
    }

    private Chronotype classifyNight(SleepingSession session) {
        LocalTime start = session.getStart().toLocalTime();
        LocalTime end = session.getEnd().toLocalTime();

        if (start.isAfter(LocalTime.of(23, 0)) && end.isAfter(LocalTime.of(9, 0))) {
            return Chronotype.OWL;
        } else if (start.isBefore(LocalTime.of(22, 0)) && end.isBefore(LocalTime.of(7, 0))) {
            return Chronotype.LARK;
        } else {
            return Chronotype.PIGEON;
        }
    }
}