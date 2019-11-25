package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;
import com.example.beepbuddy.model.User;

import java.util.List;

public class ViewReceiptActivity extends AppCompatActivity {

    TextView tv1CarPlateNumber;
    TextView tv1Date;
    TextView tv1Parking;
    TextView tv1ParkingDuration;
    TextView tv1BuildingCode;
    TextView tv1HostSuite;
    TextView tv1ParkingCost;
    
    String buildingCode;
    String carPlate;
    String hostSuite;
    
    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);
        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        this.fetchFromDB();

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
    
    private void fetchFromDB() {
        buildingCode = getIntent().getStringExtra("EXTRA_BUILDING_CODE");
        carPlate = getIntent().getStringExtra("EXTRA_CAR_PLATE");
        hostSuite = getIntent().getStringExtra("EXTRA_HOST_SUITE");
        //buildingCode = getIntent().getStringExtra("EXTRA_BUILDING_CODE");
//        final String carPlate = this.getIntent().getStringExtra("EXTRA_CAR_PLATE");
//        final String hostSuite = this.getIntent().getStringExtra("EXTRA_HOST_SUITE");
//        final String parkingDuration = this.getIntent().getStringExtra("EXTRA_PARKING_DURATION");
//        
        userViewModel.getAllUsers().observe(ViewReceiptActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> allUsers) {
                for(User user : allUsers){
                    //if(user.getPlateNumber().equals(carPlate)){
                        tv1BuildingCode.setText(buildingCode);
                        tv1CarPlateNumber.setText(carPlate);
                        tv1HostSuite.setText(hostSuite);
                        //tv1ParkingDuration.setText(parkingDuration);
                        //TODO add date, time,and charges.
                    //}
                }
            }
        });

    }

    
}
