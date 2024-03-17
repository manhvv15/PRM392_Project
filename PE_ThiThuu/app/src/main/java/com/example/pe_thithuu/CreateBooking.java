package com.example.pe_thithuu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pe_thithuu.database.SQLiteHelper;
import com.example.pe_thithuu.model.Booking;

import java.time.LocalDateTime;
import java.util.List;

public class CreateBooking extends AppCompatActivity {

    EditText custorName, serviceName, etPrice, etQuantity;

    TextView value;
    Button btSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_booking);
        custorName = findViewById(R.id.custorName);
        serviceName = findViewById(R.id.serviceName);
        etPrice = findViewById(R.id.etPrice);
        etQuantity = findViewById(R.id.etQuantity);
        value = findViewById(R.id.txtValue);
        btSave = findViewById(R.id.btSave);
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String CustorName = custorName.getText().toString();
                String ServiceName = serviceName.getText().toString();
                String priceString = etPrice.getText().toString();
                double price = Double.valueOf(priceString);
                int quantity = Integer.parseInt(etQuantity.getText().toString());
                double Value =  Double.valueOf(123);
                LocalDateTime time  = LocalDateTime.now();
                Booking booking = new Booking(CustorName,ServiceName,quantity,price,Value,time);
                SQLiteHelper db = new SQLiteHelper(CreateBooking.this);
                db.addBooking(booking);
                List<Booking> bookings = db.listAllBooking();
                Toast.makeText(CreateBooking.this, "Add successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}