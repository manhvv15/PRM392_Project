package com.example.prm392_project.dal;

import android.util.Log;

import com.example.prm392_project.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SupplierDAO {
    public Supplier getSuppierById(int id) {
        try {
            String strSelect = "select * from supplier where id =?";
            PreparedStatement ps = DAO.connection.prepareStatement(strSelect);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Supplier(rs.getInt("id"), rs.getString("name"), rs.getTimestamp("createdAt"),
                        rs.getTimestamp("deletedAt"), rs.getTimestamp("updatedAt"), rs.getBoolean("isDelete"),
                        rs.getString("image"), DAO.userDAO.getUserById(rs.getInt("createdBy")),
                        DAO.userDAO.getUserById(rs.getInt("deletedBy")), DAO.userDAO.getUserById(rs.getInt("createdBy")));
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
}
