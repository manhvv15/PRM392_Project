package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class Navigation_Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);
        drawerLayout = findViewById(R.id.drawer_layout_nav);
        materialToolbar = findViewById(R.id.material_tool_nav);
        //frameLayout = findViewById(R.id.fra);
        navigationView = findViewById(R.id.navigation_item);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,materialToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.contact){

                    Toast.makeText(Navigation_Home.this,"Contact is selected",Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.home){
                    Toast.makeText(Navigation_Home.this,"home is selected",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId()==R.id.contact){
                    Toast.makeText(Navigation_Home.this,"Contact is selected",Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.home){
                    Toast.makeText(Navigation_Home.this,"home is selected",Toast.LENGTH_SHORT).show();
                }else if(item.getItemId()==R.id.login){
                  //  Toast.makeText(Navigation_Home.this,"login is selected",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Navigation_Home.this, Login.class));
                }
                return false;
            }
        });
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