package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.prm392_project.adapter.ProductAdapter;
import com.example.prm392_project.dal.ProductDAO;
import com.example.prm392_project.model.Product;

import java.util.ArrayList;

public class Admin_Product extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> products;
    ProductDAO productDAO = new ProductDAO();
    ImageView home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_product);
        recyclerView = findViewById(R.id.productsList);
        home = findViewById(R.id.imgHomeProduct);
        products = new ArrayList<Product>();
        products.add(new Product(1,"Name1",2,3));
        products.add(new Product(2,"Name2",2,3));
        products =   productDAO.getListProduct();

        productAdapter = new ProductAdapter(products, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Admin_Product.this, Admin.class));
            }
        });
    }
}