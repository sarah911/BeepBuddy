package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;

import java.util.List;

public class ViewReceiptActivity extends AppCompatActivity {

    TextView tv1CarPlateNumber;
    TextView tv1Date;
    TextView tv1Time;
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
        tv1Date = findViewById(R.id.tv1Date);
        tv1Time = findViewById(R.id.tv1Time);
        tv1ParkingDuration = findViewById(R.id.tv1ParkingDuration);
        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);
        tv1CarPlateNumber = findViewById(R.id.tv1CarPlateNumber);
        tv1HostSuite = findViewById(R.id.tv1HostSuite);
        tv1ParkingCost = findViewById(R.id.tvParkingCost);
    }
    
    private void fetchFromDB() {
        buildingCode = this.getIntent().getStringExtra("EXTRA_BUILDING_CODE");
        carPlate = this.getIntent().getStringExtra("EXTRA_CAR_PLATE");
        hostSuite = this.getIntent().getStringExtra("EXTRA_HOST_SUITE");

//        receiptIntent.putExtra("EXTRA_BUILDING_CODE", buildingCode);
//        receiptIntent.putExtra("EXTRA_CAR_PLATE", carPlate);
//        receiptIntent.putExtra("EXTRA_HOST_SUITE", hostSuite);

        userViewModel.getAllUsers().observe(ViewReceiptActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> allUsers) {
                for(User user : allUsers){
                    //if(user.getPlateNumber().equals(carPlate)){
                        tv1BuildingCode.setText(buildingCode);
                        tv1CarPlateNumber.setText(carPlate);
                        tv1HostSuite.setText(hostSuite);

                    //}
                }
            }
        });

    }

    
}
