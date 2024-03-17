package com.example.pe_thithuu.model;

import java.time.LocalDateTime;

public class Booking {

    int id;
    private String CustomerName;
    private String ServiceName;
    private Double Price;
    private int Quantity;
    private LocalDateTime time;
    private Double Value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String serviceName) {
        ServiceName = serviceName;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public Booking() {

    }
//    public Booking(customreName, serviceName, quantity, price, value,time){
//
//    }

    public Booking(String customerName, String serviceName, int quantity, Double price, Double value, LocalDateTime time) {
        CustomerName = customerName;
        ServiceName = serviceName;
        Price = price;
        Quantity = quantity;
        this.time = time;
        Value = value;
    }
}
