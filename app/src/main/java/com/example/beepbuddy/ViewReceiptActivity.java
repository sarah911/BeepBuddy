package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ViewReceiptActivity extends AppCompatActivity {

    TextView tv1CarPlateNumber;
    TextView tv1Date;
    TextView tv1Parking;
    TextView tv1ParkingDuration;
    TextView tv1BuildingCode;
    TextView tv1HostSuite;
    TextView tv1ParkingCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);
        this.referWidgets();

    }

    private void referWidgets() {
        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);
        tv1Date = findViewById(R.id.tvDate);
        tv1Parking = findViewById(R.id.tvParking);
        tv1ParkingDuration = findViewById(R.id.tv1ParkingDuration);
        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);
        tv1CarPlateNumber = findViewById(R.id.tv1CarPlateNumber);
        tv1HostSuite = findViewById(R.id.tvHostSuite);
        tv1ParkingCost = findViewById(R.id.tvParkingCost);
    }
}
