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
    public ArrayList<Product> getListProduct() {
        ArrayList<Product> list = new ArrayList<>();
        try {
            String str = "select * from dbo.[Product] ";
            PreparedStatement ps = DAO.connection.prepareStatement(str);

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
        return list;
    }
    public Product findProductById(int id) {
        Product product = new Product();
        try {
            String str = "select * from dbo.[Product] where id = ?";
            PreparedStatement ps = DAO.connection.prepareStatement(str);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Product(rs.getInt("id"), rs.getString("name"), rs.getInt("quantity"), rs.getInt("price"),
                        DAO.supplierDAO.getSuppierById(rs.getInt("supplier")), rs.getTimestamp("createdAt"), DAO.userDAO.getUserById(rs.getInt("createdBy")),
                        rs.getBoolean("isDelete"), rs.getTimestamp("deletedAt"), DAO.userDAO.getUserById(rs.getInt("deletedBy")),
                        rs.getTimestamp("updatedAt"), DAO.userDAO.getUserById(rs.getInt("updatedBy")));
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return product;
    }
    public void update(Product p, int id) {
        try {
            String query = "update dbo.[Product] set name = ?, quantity = ?, price = ? where id = ?";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getQuantity());
            ps.setInt(3, p.getPrice());
//            ps.setInt(4, p.getSupplier().getId());
//            ps.setInt(5, p.getUpdatedBy().getId());
//            ps.setTimestamp(6, p.getUpdatedAt());
            ps.setInt(4, id);
            ps.execute();
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
    }
    public void delete(Product p, int id) {
        try {
            String query = "DELETE FROM product WHERE id = ?;";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
//            List<Storage> list = DAO.storageDAO.getStorageByProduct(id);
//            for (Storage s : list) {
//                s.setDeletedAt(p.getDeletedAt());
//                s.setDeletedBy(p.getDeletedBy());
//                s.setDelete(true);
//                DAO.storageDAO.delete(s);
//            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
    }

}
