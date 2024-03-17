package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.prm392_project.dal.UserDAO;
import com.example.prm392_project.model.User;

public class Admin_Infor extends AppCompatActivity {

    EditText name,email,phone,tk;
    Button update ;
    UserDAO userDAO ;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_infor);
        name= findViewById(R.id.edtNameAdmin);
        email= findViewById(R.id.edtEmailAdmin);
        phone= findViewById(R.id.edtPhoneAdmin);
        tk= findViewById(R.id.edtTkAdmin);
        update= findViewById(R.id.btnUpdateadmin);
        userDAO = new UserDAO();
        user  = new User();
        user =  userDAO.getInforUser("admin");
       name.setText(user.getAccount());
        email.setText(user.getEmail());
        phone.setText(user.getPhoneNumber());
        tk.setText(user.getBalance()+"");
//        update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }
}