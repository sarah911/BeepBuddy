package com.example.beepbuddy.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

    Integer parkingTime;

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

        Intent receiptIntent = new Intent(AddActivity.this, PassingActivity.class);
        startActivity(receiptIntent);
        openViewReceiptActivity();

        //calculateParkingCharges();
        getValues();



    }

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

    private void openViewReceiptActivity() {


        buildingCode = edtBuildingCode.getText().toString();
        carPlate = edtCarPlate.getText().toString();
        hostSuite = edtHostSuite.getText().toString();
        parkingDuration = edtDuration.getText().toString();

        parkingTime = Integer.parseInt(parkingDuration);

        if(parkingTime <= 1){
            parkingCharges = 4;
        } else if (parkingTime <= 3){
            parkingCharges = 8;
        } else if (parkingTime <= 10){
            parkingCharges = 12;
        } else if (parkingTime<= 24) {
            parkingCharges = 20;
//        } else{
//            //Toast.makeText(this, "Please enter valid number of hours! Less than or equal to 24 hours.",Toast.LENGTH_LONG).show();
//            parkingCharges = 0;
        }

        parkingAmount = "$" + parkingCharges;


        Intent receiptIntent = new Intent(AddActivity.this, PassingActivity.class);
        receiptIntent.putExtra("EXTRA_BUILDING_CODE", buildingCode);
        receiptIntent.putExtra("EXTRA_CAR_PLATE", carPlate);
        receiptIntent.putExtra("EXTRA_HOST_SUITE", hostSuite);
        receiptIntent.putExtra("EXTRA_PARKING_DURATION", parkingDuration);
        receiptIntent.putExtra("EXTRA_PARKING_AMOUNT", parkingAmount);
        receiptIntent.putExtra("EXTRA_DATE", strDate);
        receiptIntent.putExtra("EXTRA_TIME", strTime);
        startActivity(receiptIntent);


    }


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

