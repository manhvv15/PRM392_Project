package com.example.prm392_project.model;

import com.google.gson.Gson;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String account;
    private String password;
    private String email;
    private int role;
    private String phoneNumber;
    private int balance;
    private boolean isDelete;
    private boolean isActive;
    private Timestamp createdAt;
    private int createdBy;
    private Timestamp updatedAt;
    private int updatedBy;
    private Timestamp deletedAt;
    private int deletedBy;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
