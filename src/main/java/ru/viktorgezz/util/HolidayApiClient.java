package ru.viktorgezz.util;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class HolidayApiClient {
    private final static RestTemplate restTemplate = new RestTemplate();

    private final static String URL = "https://date.nager.at/api/v3/PublicHolidays";
    private final static String COUNTRY_CODE = "RU";
    private final static String YEAR = "2024";
    private static String api_url;

    static {
        setUrl();
    }

    private static void setUrl() {
        api_url = URL + "/" + YEAR + "/" + COUNTRY_CODE;
    }

    public static HashSet<LocalDate> setHolidays() {
        Holiday[] holidays = (restTemplate.getForObject(api_url, Holiday[].class));

        HashSet<LocalDate> holidaysUniq = new HashSet<>();
        if (holidays != null) {
            Arrays.stream(holidays)
                    .forEach(
                            holiday -> holidaysUniq.add(holiday.getDate())
                    );
        }
        return holidaysUniq;
    }

    public static class Holiday {
        private LocalDate date;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        @Override
        public String toString() {
            return "Holiday{" +
                    "date=" + date +
                    '}';
        }
    }
}

