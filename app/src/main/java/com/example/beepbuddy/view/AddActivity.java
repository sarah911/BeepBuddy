package com.example.beepbuddy.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.beepbuddy.DBAdapter;
import com.example.beepbuddy.R;
import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtBuildingCode;
    EditText edtCarPlate;
    EditText edtHostSuite;
    EditText edtDuration;

    Button btnCalculate;

    String buildingCode;
    String carPlate;
    String hostSuite;
    String parkingAmount;
    String parkingDuration ;
    Integer parkingCharges;

    DBAdapter db;

    TextView tv1Date;
    TextView tv1Time;

    String strDate;
    String strTime;


    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
        db = new DBAdapter(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("EEE, MMM d, ''yy");
        SimpleDateFormat mtformat = new SimpleDateFormat("h:mm a");
        strDate = "" + mdformat.format(calendar.getTime());
        strTime = "" + mtformat.format(calendar.getTime());

    }




    private void referWidgets() {
        edtBuildingCode = findViewById(R.id.edtBuildingCode);
        edtCarPlate = findViewById(R.id.edtCarPlate);
        edtHostSuite = findViewById(R.id.edtHostSuite);
        edtDuration = findViewById(R.id.edtDuration);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {

        Intent receiptIntent = new Intent(AddActivity.this, ViewReceiptActivity.class);
        startActivity(receiptIntent);
        //calculateParkingCharges();
        getValues();
//        switch (view.getId()){
//            case R.id.btnCalculate:
//                this.getValues();
//                //this.openViewReceiptActivity();
//                //this.calculateParkingCharges();
//
//        }

    }

//    private void saveToDB() {
//        User newUser = new User(null, null, null, null, carPlate,
//                null, null, null, null, null, null);
//        newUser.calculateParkingCharges();
//        Log.e("AddActivity", newUser.toString());
//        userViewModel.insert(newUser);
//    }

//    public void calculateParkingCharges(){
//        if(Integer.parseInt(parkingDuration) <= 1){
//            parkingCharges = 4;
//        } if (Integer.parseInt(parkingDuration) <= 3){
//            parkingCharges = 8;
//        } if (Integer.parseInt(parkingDuration) <= 10){
//            parkingCharges = 12;
//        } else {
//            parkingCharges = 20;
//        }
//
//        this.calculateAmount();
//    }
//
//    private void calculateAmount() {
//        parkingAmount = "$" + parkingCharges;
//    }


    private void getValues() {

        buildingCode = edtBuildingCode.getText().toString();
        carPlate = edtCarPlate.getText().toString();
        hostSuite = edtHostSuite.getText().toString();
        parkingDuration = edtDuration.getText().toString();

        if(Integer.parseInt(parkingDuration) <= 1){
            parkingCharges = 4;
        } if (Integer.parseInt(parkingDuration) <= 3){
            parkingCharges = 8;
        } if (Integer.parseInt(parkingDuration) <= 10){
            parkingCharges = 12;
        } else {
            parkingCharges = 20;
        }

        parkingAmount = "$" + parkingCharges;





        db.open();
        db.insertUser(buildingCode, carPlate, hostSuite, parkingDuration, strDate, strTime, parkingAmount);
        db.close();
        //this.saveToDB();

    }

//    private void openViewReceiptActivity() {
//        Intent receiptIntent = new Intent(AddActivity.this, ViewReceiptActivity.class);
//        receiptIntent.putExtra("EXTRA_BUILDING_CODE", buildingCode);
//        receiptIntent.putExtra("EXTRA_CAR_PLATE", carPlate);
//        receiptIntent.putExtra("EXTRA_HOST_SUITE", hostSuite);
//        receiptIntent.putExtra("EXTRA_PARKING_DURATION", parkingDuration);
//        //receiptIntent.putExtra("EXTRA_PARKING_AMOUNT", parkingAmount);
//        startActivity(receiptIntent);
//    }


    private void getAllUsers(){
        userViewModel.getAllUsers().observe(AddActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> allUsers) {
                for(User user : allUsers){
                    Log.e("AddActivity", user.toString());
                }
            }
        });
    }
}

