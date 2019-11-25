package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtBuildingCode;
    EditText edtCarPlate;
    EditText edtHostSuite;
    EditText edtDuration;

//    RadioGroup rdgDuration;
//    RadioButton rdbSelected;

    Button btnCalculate;

    Spinner spnDuration;

    String buildingCode;
    String carPlate;
    String hostSuite;
    Integer parkingDuration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.referWidgets();

    }

    private void referWidgets() {
        edtBuildingCode = findViewById(R.id.edtBuildingCode);
        edtCarPlate = findViewById(R.id.edtCarPlate);
        edtHostSuite = findViewById(R.id.edtHostSuite);
        edtDuration = findViewById(R.id.edtDuration);

        //rdgDuration = findViewById(R.id.rdgDuration);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        ArrayAdapter durationAdapter = ArrayAdapter.createFromResource(this, R.array.duration_array, android.R.layout.simple_spinner_dropdown_item);
        spnDuration.setAdapter(durationAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnCalculate:
                this.getValues();
                this.openViewReceiptActivity();
        }

    }

    private void openViewReceiptActivity() {
        Intent receiptIntent = new Intent(AddActivity.this, ViewReceiptActivity.class);
        receiptIntent.putExtra("EXTRA_PARKING_RECEIPT", carPlate);
        startActivity(receiptIntent);
    }

    private void getValues() {
        buildingCode = edtBuildingCode.getText().toString();
        carPlate = edtCarPlate.getText().toString();
        hostSuite = edtHostSuite.getText().toString();
        parkingDuration = Integer.parseInt(edtDuration.getText().toString());

//        rdbSelected = findViewById(rdgDuration.getCheckedRadioButtonId());

    }
}
