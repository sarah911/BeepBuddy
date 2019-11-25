package com.example.beepbuddy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnCall;
    Button btnEmail;

    @Nullable
 //   @Override
    protected void onCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);

        View root = inflater.inflate(R.layout.activity_support, container, false);
        //setContentView(R.layout.activity_support);

        btnCall = root.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        btnEmail = root.findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCall:
                this.makeCall();
                break;
            case R.id.btnEmail:
                this.sendEmail();
                break;
        }
    }

    private void makeCall(){

        Intent callIntent = new Intent(Intent.ACTION_CALL);

        callIntent.setData(Uri.parse("tel:1234567890"));

        if(ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            Log.e("SupportFragment", "Call permission not granted");
            return;
        }

        startActivity(callIntent);

    }

    private void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"smsarah911@gmail.com"});

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Email");

        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "This is a test message");

        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Please select email client"));
    }
}