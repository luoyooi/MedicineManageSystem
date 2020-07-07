package com.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-05-31 13:06
 */
public class PutStorage {
    private Integer eno;
    private Integer medId;
    private BigDecimal cost;
    private Integer quantity;
    private Integer date;
    private String handPerson;

    public PutStorage() {
    }

    public PutStorage(Integer medId, BigDecimal cost, Integer quantity, Integer date, String handPerson) {
        this.medId = medId;
        this.cost = cost;
        this.quantity = quantity;
        this.date = date;
        this.handPerson = handPerson;
    }

    public PutStorage(Integer eno, Integer medId, BigDecimal cost, Integer quantity, Integer date, String handPerson) {
        this.eno = eno;
        this.medId = medId;
        this.cost = cost;
        this.quantity = quantity;
        this.date = date;
        this.handPerson = handPerson;
    }

    @Override
    public String toString() {
        return "PutStorage{" +
                "eno=" + eno +
                ", medId=" + medId +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", date=" + date +
                ", handPerson='" + handPerson + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PutStorage storage = (PutStorage) o;
        return Objects.equals(eno, storage.eno) &&
                Objects.equals(medId, storage.medId) &&
                Objects.equals(cost, storage.cost) &&
                Objects.equals(quantity, storage.quantity) &&
                Objects.equals(date, storage.date) &&
                Objects.equals(handPerson, storage.handPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eno, medId, cost, quantity, date, handPerson);
    }

    public Integer getEno() {
        return eno;
    }

    public void setEno(Integer eno) {
        this.eno = eno;
    }

    public Integer getMedId() {
        return medId;
    }

    public void setMedId(Integer medId) {
        this.medId = medId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getHandPerson() {
        return handPerson;
    }

    public void setHandPerson(String handPerson) {
        this.handPerson = handPerson;
    }
}
