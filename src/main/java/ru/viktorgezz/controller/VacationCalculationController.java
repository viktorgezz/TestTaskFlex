package ru.viktorgezz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.viktorgezz.service.HolidayService;
import ru.viktorgezz.util.Exception.WrongParametersException;
import ru.viktorgezz.util.VacationCalculationValidator;
import ru.viktorgezz.dto.VacationPayDTO;
import ru.viktorgezz.model.VacationCalculation;
import ru.viktorgezz.service.VacationCalculationService;
import ru.viktorgezz.util.Exception.ValidationException;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
public class VacationCalculationController {

    private final VacationCalculationService vacationCalculationService;
    private final VacationCalculationValidator vacationCalculationValidator;
    private final HolidayService holidayService;

    @Autowired
    public VacationCalculationController(VacationCalculationService vacationCalculationService,
                                         VacationCalculationValidator vacationCalculationValidator,
                                         HolidayService holidayService) {
        this.vacationCalculationService = vacationCalculationService;
        this.vacationCalculationValidator = vacationCalculationValidator;
        this.holidayService = holidayService;
    }

    @GetMapping("/calculate")
    public ResponseEntity<VacationPayDTO> calculate(
            @RequestParam("averageAnnualSalary") BigDecimal averageAnnualSalary,
            @RequestParam("vacationDays") int vacationDays,
            @RequestParam(value = "vacationStart", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStart,
            @RequestParam(value = "vacationEnd", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationEnd) {
        try {
            vacationCalculationValidator.validate(averageAnnualSalary, vacationDays);
            vacationCalculationValidator.validate(vacationStart, vacationEnd, vacationDays);
        } catch (WrongParametersException e) {
            throw new ValidationException(e.getMessage());
        }

        if (vacationStart != null && vacationEnd != null) {
            vacationDays = holidayService.getDayWithoutHoliday(vacationStart, vacationEnd);
        }

        VacationCalculation vacationCalculation = new VacationCalculation(averageAnnualSalary, vacationDays);
        VacationPayDTO vacationPayDTO = new VacationPayDTO(vacationCalculationService.calculate(vacationCalculation));

        return new ResponseEntity<>(vacationPayDTO, HttpStatus.OK);
    }
}
