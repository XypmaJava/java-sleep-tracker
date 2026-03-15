package ru.yandex.practicum;

import ru.yandex.practicum.analysis.*;
import ru.yandex.practicum.io.SleepFileReader;
import ru.yandex.practicum.model.SleepingSession;
import ru.yandex.practicum.model.Chronotype;

import java.util.List;
import java.util.function.Function;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS =
            List.of(new SleepSessionCounter(),
                    new MinSleepDurationFunction(),
                    new MaxSleepDurationFunction(),
                    new AvgSleepDurationFunction(),
                    new BadQualitySleepCounter(),
                    new SleeplessNightsFunction()
            );

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("Укажите путь к файлу sleep_log.txt");
            return;
        }

        List<SleepingSession> sessions = SleepFileReader.read(args[0]);

        FUNCTIONS.stream().map(f ->
                f.apply(sessions)).forEach(r ->
                System.out.println(r.getDescription() + ": " + r.getValue()));

        UserChronotypeFunction chronotypeFunction = new UserChronotypeFunction();
        Chronotype userType = chronotypeFunction.apply(sessions);

        System.out.println("Хронотип пользователя: " + userType.name().toLowerCase());
    }
}