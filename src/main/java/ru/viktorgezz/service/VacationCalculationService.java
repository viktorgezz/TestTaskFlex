package ru.viktorgezz.service;

import org.springframework.stereotype.Service;
import ru.viktorgezz.model.VacationCalculation;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VacationCalculationService {

    public BigDecimal calculate(VacationCalculation vacationCalculation) {
        return vacationCalculation.getAverageAnnualSalary()
                .divide(new BigDecimal("29.3"), 0, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(vacationCalculation.getVacationDays()));
    }

}
