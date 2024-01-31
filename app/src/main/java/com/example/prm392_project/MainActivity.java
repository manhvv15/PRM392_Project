package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.prm392_project.dal.DBConnection;
import com.google.android.material.navigation.NavigationView;

import java.sql.Connection;

public class MainActivity extends AppCompatActivity {

    Connection connect = null;
    Button btnTest;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnTest = findViewById(R.id.btnTest);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_item);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.contact){
                    Toast.makeText(MainActivity.this,"Contact is selected",Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.home){
                    Toast.makeText(MainActivity.this,"home is selected",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
//        try {
//            DBConnection connection = new DBConnection();
//            connect = connection.createConnection();
//            if (connect != null) {
//                Toast.makeText(this, "Connection successful", Toast.LENGTH_LONG).show();
//            } else {
//                Toast.makeText(this, "Connection not establish", Toast.LENGTH_LONG).show();
//            }
//        } catch (Exception e) {
//            Log.e("", e.getMessage());
//        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();

        }
    }
}
