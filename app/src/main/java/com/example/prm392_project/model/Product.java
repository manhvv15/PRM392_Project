package com.example.prm392_project.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@AllArgsConstructor
@Data
@Builder


public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;
    private Supplier supplier;
    private Timestamp createdAt;
    private User createdBy;
    private boolean isDelete;
    private Timestamp deletedAt;
    private User deletedBy;
    private Timestamp updatedAt;
    private User updatedBy;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public Product() {

    }
    public Product(int id, String name, int quantity, int price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

}
