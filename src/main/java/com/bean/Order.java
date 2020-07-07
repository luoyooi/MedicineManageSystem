package com.bean;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author shkstart
 * @create 2020-05-31 13:03
 */
public class Order {
    private int orderId;
    private int medId;
    private int quantity;
    private BigDecimal cost;
    private BigDecimal price;
    private Integer date;
    private String handPerson;

    public Order() {
    }

    public Order(int medId, int quantity, BigDecimal cost, BigDecimal price, Integer date, String handPerson) {
        this.medId = medId;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.date = date;
        this.handPerson = handPerson;
    }

    public Order(int orderId, int medId, int quantity, BigDecimal cost, BigDecimal price, Integer date, String handPerson) {
        this.orderId = orderId;
        this.medId = medId;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.date = date;
        this.handPerson = handPerson;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", medId=" + medId +
                ", quantity=" + quantity +
                ", cost=" + cost +
                ", price=" + price +
                ", date=" + date +
                ", handPerson='" + handPerson + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return orderId == order.orderId &&
                medId == order.medId &&
                quantity == order.quantity &&
                Objects.equals(cost, order.cost) &&
                Objects.equals(price, order.price) &&
                Objects.equals(date, order.date) &&
                Objects.equals(handPerson, order.handPerson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, medId, quantity, cost, price, date, handPerson);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
