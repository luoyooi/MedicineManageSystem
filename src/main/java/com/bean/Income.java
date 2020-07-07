package com.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-06-12 14:16
 */
public class Income {
    private String months;
    private BigDecimal costSum;
    private BigDecimal priceSum;

    public Income() {
    }

    public Income(String months, BigDecimal costSum, BigDecimal priceSum) {
        this.months = months;
        this.costSum = costSum;
        this.priceSum = priceSum;
    }

    @Override
    public String toString() {
        return "Income{" +
                "months='" + months + '\'' +
                ", costSum=" + costSum +
                ", priceSum=" + priceSum +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Income income = (Income) o;
        return Objects.equals(months, income.months) &&
                Objects.equals(costSum, income.costSum) &&
                Objects.equals(priceSum, income.priceSum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(months, costSum, priceSum);
    }

    public String getMonths() {
        return months;
    }

    public void setMonths(String months) {
        this.months = months;
    }

    public BigDecimal getCostSum() {
        return costSum;
    }

    public void setCostSum(BigDecimal costSum) {
        this.costSum = costSum;
    }

    public BigDecimal getPriceSum() {
        return priceSum;
    }

    public void setPriceSum(BigDecimal priceSum) {
        this.priceSum = priceSum;
    }
}
