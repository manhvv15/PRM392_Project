package com.example.prm392_project.dal;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;

public class DAO {
    // Hash a password for the first time

    public DAO() {
        System.out.println("dao");
    }
    public static Connection connection = DBConnection.createConnection();
    public static UserDAO userDAO = new UserDAO();
}
