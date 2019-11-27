package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.widget.TextView;

import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
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
    String parkingAmount;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);
        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        this.fetchFromDB();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("EEE, MMM d, ''yy");
        SimpleDateFormat mtformat = new SimpleDateFormat("h:mm a");
        String strDate = "" + mdformat.format(calendar.getTime());
        String strTime = "" + mtformat.format(calendar.getTime());
        set(strTime);
        display(strDate);

    }

    private void set(String time){
        tv1Time = findViewById(R.id.tv1Time);
        tv1Time.setText(time);
    }

    private void display(String num) {
        tv1Date = findViewById(R.id.tv1Date);

        tv1Date.setText(num);

    }

    private void referWidgets() {
        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);

        tv1ParkingDuration = findViewById(R.id.tv1ParkingDuration);
        tv1BuildingCode = findViewById(R.id.tv1BuildingCode);
        tv1CarPlateNumber = findViewById(R.id.tv1CarPlateNumber);
        tv1HostSuite = findViewById(R.id.tv1HostSuite);
        tv1ParkingCost = findViewById(R.id.tv1ParkingCost);
    }

    private void fetchFromDB() {
        buildingCode = this.getIntent().getStringExtra("EXTRA_BUILDING_CODE");
        carPlate = this.getIntent().getStringExtra("EXTRA_CAR_PLATE");
        hostSuite = this.getIntent().getStringExtra("EXTRA_HOST_SUITE");
        parkingAmount = this.getIntent().getStringExtra("EXTRA_PARKING_AMOUNT");


        userViewModel.getAllUsers().observe(ViewReceiptActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> allUsers) {
                for(User user : allUsers){
                    //if(user.getPlateNumber().equals(carPlate)){
                    tv1BuildingCode.setText(buildingCode);
                    tv1CarPlateNumber.setText(carPlate);
                    tv1HostSuite.setText(hostSuite);
                    tv1ParkingCost.setText(parkingAmount);

                    //}
                }
            }
        });

    }


}

