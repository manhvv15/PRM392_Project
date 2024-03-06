package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ServiceCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prm392_project.R;
import com.example.prm392_project.dal.ProductDAO;
import com.example.prm392_project.dal.StorageDAO;
import com.example.prm392_project.model.Storage;

public class DetailStorerage extends AppCompatActivity {

    TextView Id, Seri, SoThe, Expiry;
    Button btnHuy, btnUpdate;
    StorageDAO storageDAO;
    Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_storerage);
        Id = findViewById(R.id.IdDetailStorerage);
        Seri = findViewById(R.id.SeriStorerage);
        SoThe = findViewById(R.id.TheStorerage);
        Expiry = findViewById(R.id.expiryStorage);
        btnHuy = findViewById(R.id.btnHuyDetailStorage);
        btnUpdate = findViewById(R.id.btnUpdateDetailStorage);
        storageDAO = new StorageDAO();
        storage = new Storage();
        Intent intent = getIntent();
        int IdStorage = intent.getIntExtra("IdStorage",-1);
        storage = storageDAO.getStorageById((IdStorage));
        Id.setText(String.valueOf(storage.getId()));
        Seri.setText(String.valueOf(storage.getSerialNumber()));
        SoThe.setText(String.valueOf(storage.getCardNumber()));
        Expiry.setText(String.valueOf(storage.getExpiredAt()));
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DetailStorerage.this, Admin_Storeage.class);
                startActivity(intent);
            }
        });
    }
}