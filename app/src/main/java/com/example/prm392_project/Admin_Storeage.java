package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prm392_project.adapter.ProductAdapter;
import com.example.prm392_project.adapter.StoreageAdapter;
import com.example.prm392_project.dal.ProductDAO;
import com.example.prm392_project.dal.StorageDAO;
import com.example.prm392_project.model.Product;
import com.example.prm392_project.model.Storage;

import java.util.ArrayList;

public class Admin_Storeage extends AppCompatActivity {

    RecyclerView recyclerView;
    StoreageAdapter storeageAdapter;
    ArrayList<Storage> storages;
    ProductDAO productDAO = new ProductDAO();
    StorageDAO storageDAO = new StorageDAO();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_storeage);
        recyclerView = findViewById(R.id.storesList);
        storages = new ArrayList<Storage>();
//        storages.add(new Storage(1,"Name1",2,3));
//        storages.add(new Storage(2,"Name2",2,3));
       // products =   productDAO.getListProduct();
        storages = storageDAO.getAllStorage();
        storeageAdapter = new StoreageAdapter(storages, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(storeageAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}