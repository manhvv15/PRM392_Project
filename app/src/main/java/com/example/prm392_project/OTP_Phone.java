package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP_Phone extends AppCompatActivity {

    EditText edtEnterNumber;
    Button btnGetOtp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_phone);
        edtEnterNumber = findViewById(R.id.edtInputMobileNumber);
        btnGetOtp =findViewById(R.id.btnGet_Otp);
        final ProgressBar progressBar = findViewById(R.id.processbar_sendding_otp);
        btnGetOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!edtEnterNumber.getText().toString().trim().isEmpty()){
                    if((edtEnterNumber.getText().toString().trim()).length()==10){
                        progressBar.setVisibility(View.VISIBLE);
                        btnGetOtp.setVisibility(View.INVISIBLE);
                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+84" + edtEnterNumber.getText().toString()
                                , 60, TimeUnit.SECONDS, OTP_Phone.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);
                                        Toast.makeText(OTP_Phone.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        btnGetOtp.setVisibility(View.VISIBLE);

                                        Intent intent = new Intent(getApplicationContext(),Activity_VerifyOTP.class);
                                        intent.putExtra("mobile",edtEnterNumber.getText().toString());
                                        intent.putExtra("backOtp",backOtp);
                                        startActivity(intent);

                                    }
                                });

                    }else{
                        Toast.makeText(OTP_Phone.this,"Please enter correct number",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(OTP_Phone.this,"Please enter mobile number",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}