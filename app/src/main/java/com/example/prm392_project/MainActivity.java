package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.prm392_project.dal.DBConnection;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    Connection connect = null;
    Button btnTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTest = findViewById(R.id.btnTest);
        try {
            DBConnection connection = new DBConnection();
            connect = connection.createConnection();
            if (connect != null) {
                 Toast.makeText(this, "Connection successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Connection not establish", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("", e.getMessage());
        }
    }
}
