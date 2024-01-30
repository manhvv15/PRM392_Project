package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm392_project.dal.DBConnection;
import com.example.prm392_project.dal.UserDAO;
import com.example.prm392_project.model.User;
import com.google.android.material.button.MaterialButton;

import java.sql.Connection;

public class Login extends AppCompatActivity {

    Connection connect = null;
    EditText edtUserName , edtPassword;
    TextView txtSignIn , txtForgot,txtOthers;
    ImageView imgFb,imgGg;
    MaterialButton btnLogin;
    UserDAO userDAO = new UserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName =findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignIn =findViewById(R.id.txtSignIn);
        txtForgot = findViewById(R.id.txtForgotpass);
        imgFb = findViewById(R.id.imgFb);
        imgGg = findViewById(R.id.imgGg);
        txtOthers = findViewById(R.id.txtOthers);
        btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                try{
//                    DBConnection connection = connect.create
                    userDAO.getUser(userName,password);
                }catch (Exception ex){
                    Log.e("", ex.getMessage());
                }
            }
        });
    }
}