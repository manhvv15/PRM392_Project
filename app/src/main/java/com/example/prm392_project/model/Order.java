package com.example.prm392_project.model;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private long id;
    private User user;
    private String status;
    private int totalAmount;
    private Product product;
    private int quantity;
    private boolean isDelete;
    private Timestamp createdAt;
    private User createdBy;
    private Timestamp updatedAt;
    private User updatedBy;
    private Timestamp deletedAt;
    private User deletedBy;
    private List<Storage> listStorage;
}
