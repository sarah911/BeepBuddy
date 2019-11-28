package com.example.beepbuddy.view;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.beepbuddy.R;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton btnCall;
    ImageButton btnEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_support);

        btnCall = findViewById(R.id.btnCall);
        btnCall.setOnClickListener(this);

        btnEmail = findViewById(R.id.btnEmail);
        btnEmail.setOnClickListener(this);

        String [] permissions = new String[]{Manifest.permission.CALL_PHONE};
        ActivityCompat.requestPermissions(SupportActivity.this, permissions, 1);

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
            Log.e("SupportActivity", "Call permission not granted");
            return;
        }

        Log.e("SupportActivity","Calling");

                startActivity(callIntent);

        Log.e("SupportActivity","not Calling");

    }

    private void sendEmail(){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setType("text/plain");

        emailIntent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{"beebbuddy@support.com"});

        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Test Email");

        emailIntent.putExtra(Intent.EXTRA_TEXT,
                "This is a test message");

        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Please select email client"));
    }
}