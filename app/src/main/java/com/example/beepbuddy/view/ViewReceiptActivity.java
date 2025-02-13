package com.example.beepbuddy.view;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.beepbuddy.DBAdapter;
import com.example.beepbuddy.R;
import com.example.beepbuddy.UserAdapter;
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


    DBAdapter db;
    Cursor users;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_receipt);

        db = new DBAdapter(this);
        db.open();
        users = db.getAllContacts();

        ListView listView = findViewById(R.id.listView);
        UserAdapter adapter = new UserAdapter(this, users);
        listView.setAdapter(adapter);

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


}

