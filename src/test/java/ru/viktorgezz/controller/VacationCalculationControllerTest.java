package ru.viktorgezz.controller;


import org.junit.Assert;
import org.junit.Test;
import ru.viktorgezz.dto.VacationPayDTO;
import ru.viktorgezz.service.HolidayService;
import ru.viktorgezz.service.VacationCalculationService;
import ru.viktorgezz.util.Exception.ValidationException;
import ru.viktorgezz.util.VacationCalculationValidator;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VacationCalculationControllerTest {

    private final VacationCalculationService vacationCalculationService;
    private final VacationCalculationValidator vacationCalculationValidator;
    private final HolidayService holidayService;

    private final VacationCalculationController vacationCalculationController;


    public VacationCalculationControllerTest() {
        this.vacationCalculationService = new VacationCalculationService();
        this.vacationCalculationValidator = new VacationCalculationValidator();
        this.holidayService = new HolidayService();
        vacationCalculationController = new VacationCalculationController(vacationCalculationService, vacationCalculationValidator, holidayService);
    }


    @Test
    public void testCalculateVacationPayWithDateSuccess() {
        BigDecimal averageAnnualSalary = BigDecimal.valueOf(10000);
        int vacationDays = 5;
        LocalDate vacationStart = LocalDate.of(2024, 6, 10);
        LocalDate vacationEnd = LocalDate.of(2024, 6, 14);

        BigDecimal expectedResult = BigDecimal.valueOf(1364);

        VacationPayDTO vacationPayDTO = vacationCalculationController.calculate(averageAnnualSalary, vacationDays, vacationStart, vacationEnd).getBody();

        Assert.assertEquals(expectedResult, vacationPayDTO.getVacationPay());
    }

    @Test
    public void testCalculateVacationPayWithoutDateSuccess() {
        BigDecimal averageAnnualSalary = BigDecimal.valueOf(10000);
        int vacationDays = 6;
        BigDecimal expectedResult = BigDecimal.valueOf(2046);

        VacationPayDTO vacationPayDTO = vacationCalculationController.calculate(averageAnnualSalary, vacationDays, null, null).getBody();
        Assert.assertEquals(expectedResult, vacationPayDTO.getVacationPay());
    }

    @Test(expected = ValidationException.class)
    public void testCalculateVacationPayWithDateFailure() {
        BigDecimal averageAnnualSalary = BigDecimal.valueOf(10000);
        int vacationDays = 2;
        LocalDate vacationStart = LocalDate.of(2024, 6, 10);
        LocalDate vacationEnd = LocalDate.of(2024, 6, 14);

        vacationCalculationController.calculate(averageAnnualSalary, vacationDays, vacationStart, vacationEnd);
    }

    @Test(expected = ValidationException.class)
    public void testCalculateVacationPayWithoutDateFailure() {
        BigDecimal averageAnnualSalary = BigDecimal.valueOf(-10000);
        int vacationDays = -2;
        LocalDate vacationStart = LocalDate.of(2024, 6, 10);
        LocalDate vacationEnd = LocalDate.of(2024, 6, 11);

        vacationCalculationController.calculate(averageAnnualSalary, vacationDays, vacationStart, vacationEnd);
    }

}
