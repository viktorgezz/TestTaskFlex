package ru.viktorgezz.service;

import org.springframework.stereotype.Service;
import ru.viktorgezz.util.HolidayApiClient;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Stream;



@Service
public class HolidayService {
    private final Set<LocalDate> holidays;

    public HolidayService() {
        holidays = HolidayApiClient.setHolidays();
    }

    public int getDayWithoutHoliday(LocalDate start, LocalDate finish) {
        return Math.toIntExact(Stream.iterate(start, date -> date.plusDays(1))
                .limit(finish.toEpochDay() - start.toEpochDay() + 1)
                .filter(date -> !isHoliday(date))
                .count());
    }

    private boolean isHoliday(LocalDate date) {
        return holidays.contains(date);
    }
}
