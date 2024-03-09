package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prm392_project.dal.UserDAO;
import com.example.prm392_project.model.User;

public class User_Infor extends AppCompatActivity {

    EditText name,email,phone,tk;
    Button update ;
    Button huy ;
    UserDAO userDAO ;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_infor);
        name= findViewById(R.id.edtNameUser);
        email= findViewById(R.id.edtEmailUser);
        phone= findViewById(R.id.edtPhoneUser);
        tk= findViewById(R.id.edtTkUser);
        update= findViewById(R.id.btnUpdateUser);
        huy= findViewById(R.id.btnHuyUser);
        userDAO = new UserDAO();
        user  = new User();
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("nameUser1");
        user =  userDAO.getInforUser(nameUser);
        name.setText(user.getAccount());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        tk.setText(user.getBalance()+"");
        //huy
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(User_Infor.this, Navigation_Home.class));
            }
        });
        //update
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}