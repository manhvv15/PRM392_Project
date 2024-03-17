package com.example.prm392_project.dal;

import android.util.Log;

import com.example.prm392_project.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public User getUser(String account, String password) {
        try{
            String query = "SELECT * FROM dbo.[User] WHERE account = ? and password = ?";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1,account);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User().builder().id(rs.getInt("id"))
                        .account(rs.getString("account")).password(rs.getString("password"))
                        .email(rs.getString("email")).role(rs.getInt("role"))
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getInt("balance"))
                        .isActive(rs.getBoolean("isActive")).isDelete(rs.getBoolean("isDelete"))
                        .createdAt(rs.getTimestamp("createdAt")).createdBy(rs.getInt("createdBy"))
                        .deletedAt(rs.getTimestamp("deletedAt")).deletedBy(rs.getInt("deletedBy"))
                        .updatedAt(rs.getTimestamp("updatedAt")).updatedBy(rs.getInt("updatedBy"))
                        .build();
            }
        }catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
    public User getUserById(int id) {
        try {
            String query = "SELECT * from user where id = ? and isDelete = false and isActive = true";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("account"), rs.getString("password"),
                        rs.getString("email"), rs.getInt("role"), rs.getString("phoneNumber"), rs.getInt("balance"),
                        rs.getBoolean("isDelete"), rs.getBoolean("isActive"), rs.getTimestamp("createdAt"),
                        rs.getInt("createdBy"), rs.getTimestamp("updatedAt"), rs.getInt("updatedBy"),
                        rs.getTimestamp("deletedAt"), rs.getInt("deletedBy"));
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
    public User getInforUser(String account) {
        try{
            String query = "SELECT * FROM dbo.[User] WHERE account = ? ";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1,account);
          //  ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new User().builder().id(rs.getInt("id"))
                        .account(rs.getString("account")).password(rs.getString("password"))
                        .email(rs.getString("email")).role(rs.getInt("role"))
                        .phoneNumber(rs.getString("phoneNumber")).balance(rs.getInt("balance"))
                        .isActive(rs.getBoolean("isActive")).isDelete(rs.getBoolean("isDelete"))
                        .createdAt(rs.getTimestamp("createdAt")).createdBy(rs.getInt("createdBy"))
                        .deletedAt(rs.getTimestamp("deletedAt")).deletedBy(rs.getInt("deletedBy"))
                        .updatedAt(rs.getTimestamp("updatedAt")).updatedBy(rs.getInt("updatedBy"))
                        .build();
            }
        }catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
//    public User getInfoPassword(String account) {
//        try{
//            String query = "SELECT * FROM dbo.[User] WHERE account = ? ";
//            PreparedStatement ps = DAO.connection.prepareStatement(query);
//            ps.setString(1,account);
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()){
//                //return new User(rs.getString("password"));
//                return user.getPassword(rs.getString("password"));
//
//            }
//        }catch (Exception ex){
//            Log.e("", ex.getMessage());
//        }
//        return null;
//    }
    public User user = new User();
    public void add(User user){
        try{
            String query = "INSERT INTO dbo.[User] (account, password, email, role, isDelete, isActive, createdAt)" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1,user.getAccount());ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setInt(4, user.getRole());
            ps.setBoolean(5, user.isDelete());
            ps.setBoolean(6, user.isActive());
            ps.setTimestamp(7, user.getCreatedAt());
            ps.execute();
        }catch (Exception ex){
            Log.e("", ex.getMessage());
        }
    }
    public boolean isAccountAvailable(String account) {
        try {
            String query = "SELECT * from dbo.[User] where account = ?";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return true;
    }
    public boolean  checkUser(String account, String password) {
        try {
            String query = "SELECT * FROM dbo.[User] WHERE account = ? and password = ?";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1, account);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return false;
    }
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getUser("admin","123"));
    }
}
