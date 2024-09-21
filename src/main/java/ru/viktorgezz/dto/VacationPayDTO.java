package ru.viktorgezz.dto;

import java.math.BigDecimal;

public class VacationPayDTO {

    private BigDecimal vacationPay;

    public VacationPayDTO() {
    }

    public VacationPayDTO(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

    public BigDecimal getVacationPay() {
        return vacationPay;
    }

    public void setVacationPay(BigDecimal vacationPay) {
        this.vacationPay = vacationPay;
    }

    @Override
    public String toString() {
        return "VocationPayDTO{" +
                "vacationPay=" + vacationPay +
                '}';
    }
}
