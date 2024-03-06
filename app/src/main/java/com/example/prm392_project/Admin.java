package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.prm392_project.model.Product;

public class Admin extends AppCompatActivity {

    Button khoHang,sanPham,donHang,giaoDich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        khoHang = findViewById(R.id.btnKhoHang);
        sanPham = findViewById(R.id.btnSanPham);
        donHang = findViewById(R.id.btnDonHang);
        giaoDich = findViewById(R.id.btnTransaction);
        //kho hang
        khoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Admin_Storeage.class);
                startActivity(intent);
            }
        });
        //san pham
        sanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Admin_Product.class);
                startActivity(intent);
            }
        });
        //don hang
        donHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Admin_Product.class);
                startActivity(intent);
            }
        });
        /// giao dich
        giaoDich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Admin.this, Admin_Storeage.class);
                startActivity(intent);
            }
        });
    }
}