package com.example.prm392_project;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm392_project.dal.DBConnection;
import com.example.prm392_project.dal.UserDAO;
import com.example.prm392_project.model.User;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.util.Arrays;

public class Login extends AppCompatActivity {

    Connection connect = null;
    EditText edtUserName, edtPassword;
    TextView txtSignIn, txtForgot, txtOthers;
    ImageView imgFb, imgGg;
    MaterialButton btnLogin;
    UserDAO userDAO = new UserDAO();

    BCrypt bCrypt = new BCrypt();
    CallbackManager callbackManager;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    public  String login = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserName = findViewById(R.id.edtUserName);
        edtPassword = findViewById(R.id.edtPassword);
        txtSignIn = findViewById(R.id.txtSignIn);
        txtForgot = findViewById(R.id.txtForgotpass);
        imgFb = findViewById(R.id.imgFb);
        imgGg = findViewById(R.id.imgGg);
        txtOthers = findViewById(R.id.txtOthers);
        btnLogin = (MaterialButton) findViewById(R.id.btnLogin);
        //login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = edtUserName.getText().toString();
                String password = edtPassword.getText().toString();
                String hashed = bCrypt.hashpw(password, bCrypt.gensalt());
                try {

                    String passwordDB = userDAO.getInforUser(userName).getPassword();
                    //    String passwordDB =   userDAO.getInfoPassword(userName).toString();
                    if (passwordDB != null) {
                        if (bCrypt.checkpw(password, passwordDB)) {
                            Toast.makeText(Login.this, "Login successfully", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(Login.this, Navigation_Home.class));
                        } else
                            Toast.makeText(Login.this, "Login Fail", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Login.this, "Account does not exist", Toast.LENGTH_LONG).show();
                    }

                } catch (Exception ex) {
                    Log.e("", ex.getMessage());
                    Toast.makeText(Login.this, "Account does not exist", Toast.LENGTH_LONG).show();
                }
            }
        });
        //login with fb
        callbackManager = CallbackManager.Factory.create();
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        if(accessToken!=null&&accessToken.isExpired()==false){
            startActivity(new Intent(Login.this, Navigation_Home.class));
            finish();
        }
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {

                        startActivity(new Intent(Login.this, Navigation_Home.class));
                        finish();
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        imgFb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = "2";
                SharedPreferences preferences = getSharedPreferences("login_infor", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("login", login);

                editor.commit();
                LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("public_profile"));
            }
        });
        //login with google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        imgGg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login = "3";
                SharedPreferences preferences = getSharedPreferences("login_infor", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("login", login);

                editor.commit();
                signIn();
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                navigationTosecondActivity();
            } catch (Exception ex) {
                Log.e("", ex.getMessage());
            }
        }
    }

    private void navigationTosecondActivity() {
        finish();
        Intent intent = new Intent(Login.this, Navigation_Home.class);
        startActivity(intent);
    }

    void signIn() {
        Intent signinIntent = gsc.getSignInIntent();
        startActivityForResult(signinIntent, 1000);
    }

}