package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InforOrder extends AppCompatActivity {

    TextView txtNhaPhatHanh,txtPrice;
    EditText edtNumber,edtEmail,edtNote;
    Button btnThanhToan,btnHuy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_order);
        txtNhaPhatHanh = findViewById(R.id.txtNhaPhatHanh);
        txtPrice = findViewById(R.id.txtPrice);
        edtNumber = findViewById(R.id.edtNumber);
        edtEmail = findViewById(R.id.edtEmail);
        edtNote = findViewById(R.id.edtNote);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnHuy = findViewById(R.id.btnHuy);
    }
}