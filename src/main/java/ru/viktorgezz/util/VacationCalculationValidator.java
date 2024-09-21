package ru.viktorgezz.util;

import org.springframework.stereotype.Component;
import ru.viktorgezz.util.Exception.WrongParametersException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class VacationCalculationValidator {

    public void validate(BigDecimal averageAnnualSalary, int vacationDays) throws WrongParametersException {
        StringBuilder errors = new StringBuilder();

        if (averageAnnualSalary == null) {
            errors.append("Средняя годовая зарплата не должна быть пустой\n");
        }

        if (averageAnnualSalary != null && averageAnnualSalary.compareTo(BigDecimal.ZERO) <= 0) {
            errors.append("Средняя годовая зарплата должна быть больше нуля\n");
        }

        if (vacationDays <= 0) {
            errors.append("Количество дней должно быть больше нуля\n");
        }

        String errorsResp = errors.toString();

        if (!errorsResp.isEmpty()) {
            throw new WrongParametersException(errorsResp);
        }
    }

    public void validate(LocalDate vacationStart, LocalDate vacationEnd, int vacationDays) throws WrongParametersException {
        if (vacationStart == null || vacationEnd == null) {
            return;
        }
        StringBuilder errors = new StringBuilder();

        if (vacationEnd.toEpochDay() - vacationStart.toEpochDay() + 1 < 0) {
            errors.append("Дата начала отпуска позже даты окончание отпуска\n");
        } else if (vacationDays != (vacationEnd.toEpochDay() - vacationStart.toEpochDay() + 1)) {
            int diffDays = (vacationDays - Math.toIntExact((vacationEnd.toEpochDay() - vacationStart.toEpochDay() + 1)));
            errors.append("Количество дней отпуска не равняется промежутку дней между датами. Между днями и промежутком разница: ")
                    .append(diffDays)
                    .append("\n");
        }

        String errorsResp = errors.toString();

        if (!errorsResp.isEmpty()) {
            throw new WrongParametersException(errorsResp);
        }
    }
}
