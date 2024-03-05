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
    public static TransactionsDAO transactionsDAO = new TransactionsDAO();
    public static OrderDAO orderDAO = new OrderDAO();
    public static OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
    public static SupplierDAO supplierDAO = new SupplierDAO();
    public static ProductDAO productDAO = new ProductDAO();
    public static StorageDAO storageDAO = new StorageDAO();
    public static NoticeDAO noticeDAO = new NoticeDAO();
}
