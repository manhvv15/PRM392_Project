package com.example.prm392_project.dal;

import android.util.Log;

import com.example.prm392_project.model.Storage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StorageDAO {
    public ArrayList<Storage> getAllStorage() {
        ArrayList<Storage> list = new ArrayList<>();
        try {
            String query = "select * from dbo.[storage] ";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), DAO.productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), DAO.userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        DAO.userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), DAO.userDAO.getUserById(rs.getInt("deletedBy"))));
            }
        }catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return list;
    }
    public Storage getStorageById(long id) {

        try {
            String query = "select * from dbo.[storage] where id = ?;";
            PreparedStatement ps = DAO.connection.prepareStatement(query);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Storage(rs.getLong("id"), rs.getString("serialNumber"), rs.getString("cardNumber"),
                        rs.getTimestamp("expiredAt"), DAO.productDAO.findProductById(rs.getInt("productId")), rs.getBoolean("isUsed"),
                        rs.getBoolean("isDelete"),rs.getTimestamp("createdAt"), DAO.userDAO.getUserById(rs.getInt("createdBy")), rs.getTimestamp("updatedAt"),
                        DAO.userDAO.getUserById(rs.getInt("updatedBy")), rs.getTimestamp("deletedAt"), DAO.userDAO.getUserById(rs.getInt("deletedBy")));
            }
        } catch (Exception ex){
            Log.e("", ex.getMessage());
        }
        return null;
    }
}
