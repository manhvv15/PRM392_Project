package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.prm392_project.adapter.ProductAdapter;
import com.example.prm392_project.model.Product;

import java.util.ArrayList;

public class Admin_Product extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        recyclerView = findViewById(R.id.productsList);
        products = new ArrayList<Product>();
        products.add(new Product(1,"Name1",2,3));
        products.add(new Product(2,"Name2",2,3));
        productAdapter = new ProductAdapter(products, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}