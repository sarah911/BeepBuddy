package com.example.beepbuddy.view;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.beepbuddy.R;
import com.example.beepbuddy.view.MainActivity;
import com.example.beepbuddy.view.ViewReceiptActivity;
import com.example.beepbuddy.viewmodel.UserViewModel;

public class PassingActivity extends AppCompatActivity implements View.OnClickListener{


    TextView tv1CarPlateNumber;
    TextView tv1Date;
    TextView tv1Time;
    TextView tv1ParkingDuration;
    TextView tv1BuildingCode;
    TextView tv1HostSuite;
    TextView tv1ParkingCost;

    String parkingDuration;
    String buildingCode;
    String carPlate;
    String hostSuite;
    String parkingAmount;
    String strDate;
    String strTime;

    Button btnMainPage;
    Button btnListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passing);
        this.referWidgets();
        this.callIntent();
    }



    private void referWidgets() {

        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);
        tv1CarPlateNumber = findViewById(R.id.tv1CarPlateNumber);
        tv1HostSuite = findViewById(R.id.tv1HostSuite);
        tv1ParkingDuration = findViewById(R.id.tv1ParkingDuration);
        tv1ParkingCost = findViewById(R.id.tv1ParkingCost);
        tv1Time = findViewById(R.id.tv1Time);
        tv1Date = findViewById(R.id.tv1Date);
        btnMainPage = findViewById(R.id.btnMainPage);
        btnMainPage.setOnClickListener(this);
        btnListView = findViewById(R.id.btnListView);
        btnListView.setOnClickListener(this);


    }

    private void callIntent() {

        buildingCode = getIntent().getStringExtra("EXTRA_BUILDING_CODE");
        carPlate = getIntent().getStringExtra("EXTRA_CAR_PLATE");
        hostSuite = getIntent().getStringExtra("EXTRA_HOST_SUITE");
        parkingDuration = getIntent().getStringExtra("EXTRA_PARKING_DURATION");
        parkingAmount = getIntent().getStringExtra("EXTRA_PARKING_AMOUNT");
        strDate = getIntent().getStringExtra("EXTRA_DATE");
        strTime = getIntent().getStringExtra("EXTRA_TIME");

        tv1BuildingCode.setText(buildingCode);
        tv1CarPlateNumber.setText(carPlate);
        tv1HostSuite.setText(hostSuite);
        tv1ParkingDuration.setText(parkingDuration);
        tv1ParkingCost.setText(parkingAmount);
        tv1Date.setText(strDate);
        tv1Time.setText(strTime);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnMainPage:
                this.openMainPage();
                break;
            case R.id.btnListView:
                this.openListView();
                break;
        }
    }

    void openMainPage() {
        Intent intentM = new Intent(this, MainActivity.class);
        this.startActivity(intentM);
    }

    void openListView() {
        Intent intentL = new Intent(this, ViewReceiptActivity.class);
        this.startActivity(intentL);
    }
}
