package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONObject;

public class Navigation_Home extends AppCompatActivity {

    DrawerLayout drawerLayout;
    MaterialToolbar materialToolbar;
    NavigationView navigationView;
    FrameLayout frameLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ImageView imgAva;
    TextView txtName,txtNameUser,txtXinChao;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView imgViettel,imgVinaphone,imgVietnammobile,imgMobiphone,imgTru,imgCong;
    TextView txt10,txt20,txt30,txt50,txt100,txt200,txt300,txt500,txtSoLuong;
    Button btnMuangay;
    int x = 1;
    String SL = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_home);
//        txtName = findViewById(R.id.txtName);
//        txtName.setText("13");
        txt10 = findViewById(R.id.txt10);
        txt20 = findViewById(R.id.txt20);
        txt30 = findViewById(R.id.txt30);
        txt50 = findViewById(R.id.txt50);
        txt100 = findViewById(R.id.txt100);
        txt200 = findViewById(R.id.txt200);
        txt300 = findViewById(R.id.txt300);
        txt500 = findViewById(R.id.txt500);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        btnMuangay= findViewById(R.id.btnMuangay);
        imgTru = findViewById(R.id.imgTru);
        imgCong= findViewById(R.id.imgCong);
        imgViettel = findViewById(R.id.imgviettel);
        imgVinaphone = findViewById(R.id.imgVinaphone);
        imgVietnammobile = findViewById(R.id.imgVietnammobile);
        imgMobiphone = findViewById(R.id.imgMobiphone);
        txtNameUser = findViewById(R.id.txtNameUser);
        txtXinChao = findViewById(R.id.txtXinchao);
        drawerLayout = findViewById(R.id.drawer_layout_nav);
        materialToolbar = findViewById(R.id.material_tool_nav);
        //frameLayout = findViewById(R.id.fra);
        navigationView = findViewById(R.id.navigation_item);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,materialToolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        imgTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SL = txtSoLuong.getText().toString();

                x = Integer.parseInt(SL) - 1;
                txtSoLuong.setText(x+"");
            }
        });
        imgCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SL = txtSoLuong.getText().toString();

                x = Integer.parseInt(SL) + 1;
                txtSoLuong.setText(x+"");
            }
        });
        materialToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(item.getItemId()==R.id.order){

                    Toast.makeText(Navigation_Home.this,"Order is selected",Toast.LENGTH_SHORT).show();
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
                }else if(item.getItemId()==R.id.infor){
                    Intent intent = getIntent();
                    String nameUser = intent.getStringExtra("nameUser");
                    //  Toast.makeText(Navigation_Home.this,"login is selected",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Navigation_Home.this, User_Infor.class);
                    intent.putExtra("nameUser1",nameUser);
                    startActivity(i);
                }
                else if(item.getItemId()==R.id.register){
                    //  Toast.makeText(Navigation_Home.this,"login is selected",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Navigation_Home.this, Register.class));
                }else if(item.getItemId()==R.id.logout){
                    //  Toast.makeText(Navigation_Home.this,"login is selected",Toast.LENGTH_SHORT).show();
                    //if(login)
                    SharedPreferences preferences = getSharedPreferences("login_infor", MODE_PRIVATE);
                    String login = preferences.getString("login","");
                    if(Integer.parseInt(login)==1){

                    }else  if(Integer.parseInt(login)==2){
                        signOutFacebook();
                    }else  if(Integer.parseInt(login)==3){
                        signOutGoogle();
                    }


                }
                return false;
            }
        });
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            txtXinChao.setText(personName);
            txtNameUser.setText(personEmail);

        }
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try{
                            String fullName = object.getString("name");
                            txtNameUser.setText(fullName);
                        }catch (Exception ex){}
                        // Application code
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }
    void signOutGoogle(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(Navigation_Home.this, Login.class));
            }
        });
    }
    void signOutFacebook(){
        LoginManager.getInstance().logOut();
        startActivity(new Intent(Navigation_Home.this,Login.class));
        finish();
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