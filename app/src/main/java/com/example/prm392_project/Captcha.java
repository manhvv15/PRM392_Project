package com.example.prm392_project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.safetynet.SafetyNet;

import android.graphics.Color;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNetApi;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class Captcha extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {

    CheckBox cbCaptcha;
    GoogleApiClient googleApiClient;
    String SiteKey = "6LfoY3wpAAAAAGm3wxDEk3aFCx82FDr1xM8X8ZQu";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);

        cbCaptcha = findViewById(R.id.cbCaptcha);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(Captcha.this).build();
        googleApiClient.connect();
        cbCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbCaptcha.isChecked()){
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if((status!=null)&&status.isSuccess()){
                                        Toast.makeText(Captcha.this, "Verification successfull", Toast.LENGTH_SHORT).show();
                                        cbCaptcha.setTextColor(Color.BLUE);
                                    }
                                }
                            });
                }else{
                    cbCaptcha.setTextColor(Color.RED);
                    Toast.makeText(Captcha.this, "Verification not done", Toast.LENGTH_SHORT).show();
                }
            }
//
        });



    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}