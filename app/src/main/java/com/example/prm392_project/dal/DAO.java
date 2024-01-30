package com.example.prm392_project.dal;

import java.sql.Connection;

public class DAO {
    public DAO() {
        System.out.println("dao");
    }
    public static Connection connection = DBConnection.createConnection();
    public static UserDAO userDAO = new UserDAO();
}
