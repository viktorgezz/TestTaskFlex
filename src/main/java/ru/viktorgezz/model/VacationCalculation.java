package ru.viktorgezz.model;

import java.math.BigDecimal;

public class VacationCalculation {

    private BigDecimal averageAnnualSalary;

    private int vacationDays;

    public VacationCalculation() {
    }

    public VacationCalculation(BigDecimal averageAnnualSalary, int vacationDays) {
        this.averageAnnualSalary = averageAnnualSalary;
        this.vacationDays = vacationDays;
    }

    public BigDecimal getAverageAnnualSalary() {
        return averageAnnualSalary;
    }

    public void setAverageAnnualSalary(BigDecimal averageAnnualSalary) {
        this.averageAnnualSalary = averageAnnualSalary;
    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    @Override
    public String toString() {
        return "VacationPayDTO{" +
                "averageAnnualSalary=" + averageAnnualSalary +
                ", vacationDays=" + vacationDays +
                '}';
    }
}
