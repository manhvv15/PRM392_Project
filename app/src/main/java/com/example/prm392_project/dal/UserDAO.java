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

    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        System.out.println(userDAO.getUser("admin","123"));
    }
}
