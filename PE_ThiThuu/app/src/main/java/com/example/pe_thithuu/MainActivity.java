package com.example.pe_thithuu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pe_thithuu.adapter.RecycleViewAdapter;
import com.example.pe_thithuu.database.SQLiteHelper;
import com.example.pe_thithuu.model.Booking;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btCreate;
    RecyclerView recyclerView;
    RecycleViewAdapter adapter;
    List<Booking> list;
    SQLiteHelper db= new SQLiteHelper(MainActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCreate= findViewById(R.id.btCreate);
        btCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, CreateBooking.class);
                startActivity(intent);
            }
        });
        recyclerView=findViewById(R.id.recycleList);

        list= db.listAllBooking();
//        for (Food i: list){
//            System.out.println(i.toString());
//        }

        adapter= new RecycleViewAdapter(MainActivity.this, list);
        LinearLayoutManager manager= new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }
}