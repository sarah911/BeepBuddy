package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.beepbuddy.db.UserDao;
import com.example.beepbuddy.db.UserRepository;
import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;
import com.example.beepbuddy.db.UserRepository;

import java.util.Date;
import java.util.List;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtBuildingCode;
    EditText edtCarPlate;
    EditText edtHostSuite;
    EditText edtDuration;

//    RadioGroup rdgDuration;
//    RadioButton rdbSelected;

    Button btnCalculate;

    //Spinner spnDuration;

    String buildingCode;
    String carPlate;
    String hostSuite;
    Integer parkingDuration;

    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.referWidgets();
        userViewModel = new UserViewModel(getApplication());
    }



    private void referWidgets() {
        edtBuildingCode = findViewById(R.id.edtBuildingCode);
        edtCarPlate = findViewById(R.id.edtCarPlate);
        edtHostSuite = findViewById(R.id.edtHostSuite);
        edtDuration = findViewById(R.id.edtDuration);

        //rdgDuration = findViewById(R.id.rdgDuration);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        //ArrayAdapter durationAdapter = ArrayAdapter.createFromResource(this, R.array.duration_array, android.R.layout.simple_spinner_dropdown_item);
        //spnDuration.setAdapter(durationAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalculate:
                this.getValues();
                this.openViewReceiptActivity();
                this.saveToDB();
        }

    }

    private void saveToDB() {
        User newUser = new User(null, null, null, null, carPlate,
                null, null, null, null, null, null);
        newUser.calculateParkingCharges();
        Log.e("AddActivity", newUser.toString());
        userViewModel.insert(newUser);
    }

    private void openViewReceiptActivity() {
        Intent receiptIntent = new Intent(AddActivity.this, ViewReceiptActivity.class);
        receiptIntent.putExtra("EXTRA_BUILDING_CODE", buildingCode);
        receiptIntent.putExtra("EXTRA_CAR_PLATE", carPlate);
        receiptIntent.putExtra("EXTRA_HOST_SUITE", hostSuite);
        receiptIntent.putExtra("EXTRA_PARKING_DURATION", parkingDuration);
        startActivity(receiptIntent);
    }

    private void getValues() {
        buildingCode = edtBuildingCode.getText().toString();
        carPlate = edtCarPlate.getText().toString();
        hostSuite = edtHostSuite.getText().toString();
        parkingDuration = Integer.parseInt(edtDuration.getText().toString());

//        rdbSelected = findViewById(rdgDuration.getCheckedRadioButtonId());

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
