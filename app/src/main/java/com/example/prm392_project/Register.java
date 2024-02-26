package com.example.prm392_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm392_project.dal.UserDAO;
import com.example.prm392_project.model.User;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Register extends AppCompatActivity {

    TextView txtSignUp;
    EditText edtUserName, edtPassword, edtRepassword, edtEmail;
    Button btnRegister;
    UserDAO ud = new UserDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtSignUp = findViewById(R.id.signuptitle);
        edtUserName = findViewById(R.id.username);
        edtPassword = findViewById(R.id.password);
        edtRepassword = findViewById(R.id.repassword);
        edtEmail = findViewById(R.id.email);
        btnRegister = findViewById(R.id.signupbtn);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success = true;
                if (edtUserName.getText().toString().length() <= 0
                        && edtPassword.getText().toString().length() <= 0
                        && edtRepassword.getText().toString().length() <= 0
                        && edtEmail.getText().toString().length() <= 0) {
                    success = false;
                    Toast.makeText(Register.this, "Please fill all the information", Toast.LENGTH_SHORT).show();
                }
                if (!edtPassword.getText().toString().equals(edtRepassword.getText().toString())) {
                    success = false;
                    Toast.makeText(Register.this, "Please the correct password", Toast.LENGTH_SHORT).show();
                }
                if(!ud.isAccountAvailable(edtUserName.getText().toString())){
                    success = false;
                    Toast.makeText(Register.this, "Account is existed", Toast.LENGTH_SHORT).show();
                }
                if (success) {
                    BCrypt bCrypt = new BCrypt();
                    LocalDateTime now = LocalDateTime.now();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    String formattedDateTime = now.format(formatter);

                    Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

                    User newUser = new User(edtUserName.getText().toString(), bCrypt.hashpw(edtPassword.getText().toString(), bCrypt.gensalt()), edtEmail.getText().toString(), 1, false, false, timestamp);
                    ud.add(newUser);
                    Toast.makeText(Register.this, "Register successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this,Login.class));
                }
            }
        });
    }
}