package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm392_project.adapter.ProductAdapter;
import com.example.prm392_project.dal.ProductDAO;
import com.example.prm392_project.model.Product;

public class DetailProduct extends AppCompatActivity {

    TextView Id, Name, Price, Number;
    Button btnHuy, btnUpdate;
    ProductDAO productDAO;
    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        Id = findViewById(R.id.IdDetail);
        Name = findViewById(R.id.NameDetail);
        Price = findViewById(R.id.PriceDetail);
        Number = findViewById(R.id.NumberDetail);
        btnHuy = findViewById(R.id.btnHuyDetail);
        btnUpdate = findViewById(R.id.btnUpdateDetail);
        productDAO = new ProductDAO();
        product = new Product();
        Intent intent = getIntent();
        int IdProduct = intent.getIntExtra("IdProduct",-1);
        product = productDAO.findProductById((IdProduct));
        Id.setText(String.valueOf(product.getId()));
        Name.setText(product.getName());
        Price.setText(String.valueOf(product.getPrice()));
        Number.setText(String.valueOf(product.getQuantity()));

       // int id = Integer.parseInt(Id.getText().toString());
        String name = Name.getText().toString();
        int price = Integer.parseInt(Price.getText().toString());
        int quantity = Integer.parseInt(Number.getText().toString());
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailProduct.this, Admin_Product.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //upate
                product.setName(name);
                product.setPrice(price);
                product.setQuantity(quantity);
                productDAO.update(product,IdProduct);
                Intent intent = new Intent(DetailProduct.this, Admin_Product.class);
                startActivity(intent);
            }
        });
    }
}