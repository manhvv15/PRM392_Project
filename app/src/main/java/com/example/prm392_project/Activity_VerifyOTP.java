package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Activity_VerifyOTP extends AppCompatActivity {

    EditText edtOtp1,edtOtp2,edtOtp3,edtOtp4,edtOtp5,edtOtp6;
    TextView txtshowNumber;
    Button btnVerifyOtp;
    String getOtpBackend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);
        edtOtp1 = findViewById(R.id.inputOtp1);
        edtOtp2 = findViewById(R.id.inputOtp2);
        edtOtp3 = findViewById(R.id.inputOtp3);
        edtOtp4 = findViewById(R.id.inputOtp4);
        edtOtp5 = findViewById(R.id.inputOtp5);
        edtOtp6 = findViewById(R.id.inputOtp6);
        txtshowNumber = findViewById(R.id.txtmobileshownumber);
      final ProgressBar  progressBarVerifyOtp = findViewById(R.id.processbar_sendding_otp);
        txtshowNumber.setText(String.format("+84-%s",getIntent().getStringExtra("mobile")));
        btnVerifyOtp =findViewById(R.id.btnSubmit_Otp);
        getOtpBackend = getIntent().getStringExtra("backOtp");
        btnVerifyOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtOtp1.getText().toString().trim().isEmpty()
                        &&!edtOtp2.getText().toString().trim().isEmpty()
                        &&!edtOtp3.getText().toString().trim().isEmpty()
                        &&!edtOtp4.getText().toString().trim().isEmpty()
                        &&!edtOtp5.getText().toString().trim().isEmpty()
                        &&!edtOtp6.getText().toString().trim().isEmpty()){
                    String enterCodeOtp = edtOtp1.getText().toString()+
                            edtOtp2.getText().toString()+
                            edtOtp3.getText().toString()+
                            edtOtp4.getText().toString()+
                            edtOtp5.getText().toString()+
                            edtOtp6.getText().toString();
                    if(getOtpBackend!=null){
                        progressBarVerifyOtp.setVisibility(View.VISIBLE);
                        btnVerifyOtp.setVisibility(View.INVISIBLE);
                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(getOtpBackend,enterCodeOtp);
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBarVerifyOtp.setVisibility(View.GONE);
                                btnVerifyOtp.setVisibility(View.VISIBLE);
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }else{
                                    Toast.makeText(Activity_VerifyOTP.this,"Enter the correct otp",Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                    }else{
                        Toast.makeText(Activity_VerifyOTP.this,"Otp verify",Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(Activity_VerifyOTP.this,"Please enter all number",Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberOtpMove();

        TextView resendlabel = findViewById(R.id.txtResendOtp);
        resendlabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber("+84" + getIntent().getStringExtra("mobile")
                        , 60, TimeUnit.SECONDS, Activity_VerifyOTP.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {

                                Toast.makeText(Activity_VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getOtpBackend = newbackOtp;
                                Toast.makeText(Activity_VerifyOTP.this, "OTP sended successfully", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    private void numberOtpMove() {
        edtOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edtOtp2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edtOtp3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edtOtp4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edtOtp5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        edtOtp5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.toString().trim().isEmpty()){
                    edtOtp6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}