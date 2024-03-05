package com.example.prm392_project.dal;

import android.util.Log;

import com.example.prm392_project.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {
    public ArrayList<Product> getListProductBySupplier(int id) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String str = "select * from product where supplier = ? and isDelete = false";
            PreparedStatement ps = DAO.connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"),
                        DAO.supplierDAO.getSuppierById(rs.getInt("supplier")), rs.getTimestamp("createdAt"), DAO.userDAO.getUserById(rs.getInt("createdBy")),
                        rs.getBoolean("isDelete"), rs.getTimestamp("deletedAt"), DAO.userDAO.getUserById(rs.getInt("deletedBy")),
                        rs.getTimestamp("updatedAt"), DAO.userDAO.getUserById(rs.getInt("updatedBy"))));
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
}
