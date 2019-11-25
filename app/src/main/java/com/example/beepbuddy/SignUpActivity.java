package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.beepbuddy.model.User;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    String firstName;
    String lastName;
    String phoneNumber;
    String email;
    String password;
    String confirmPass;
    String paymentType;
    String cardName;
    String cvv;
    String carPlate;
    String expDate;
    String cardNumber;


    EditText edtCardNumber;
    EditText edtFirstName;
    EditText edtLastName;
    EditText edtPhone;
    EditText edtEmail;
    EditText edtPass;
    EditText edtConfirmPass;
    EditText edtExpDate;
    EditText edtCVV;
    EditText edtCardName;
    EditText edtPlate;

    Spinner spnMonth;
    Spinner spnYear;


    RadioGroup rdoPayment;
    RadioButton rdoSelected;
    Button btnSubmit;
    Button btnCancel;



    public static final String EXTRA_REPLY = "com.example.beepbuddy.REPLY";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.referWidgets();

    }

    private void referWidgets(){
        edtFirstName = findViewById(R.id.edt_firstname);
        edtLastName = findViewById(R.id.edt_lastname);
        edtPhone = findViewById(R.id.edt_phone);
        edtEmail = findViewById(R.id.edt_email);
        edtPass = findViewById(R.id.edt_password);
        edtConfirmPass = findViewById(R.id.edt_confirm_password);
        edtCVV = findViewById(R.id.edt_cvv);
        edtCardName = findViewById(R.id.edt_cardName);
        edtPlate = findViewById(R.id.edt_plate);
        spnMonth = findViewById(R.id.spnMonth);
        spnYear = findViewById(R.id.spnYear);
        edtCardName = findViewById(R.id.edt_card_number);

        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this, R.array.month_array, android.R.layout.simple_spinner_dropdown_item);
        spnMonth.setAdapter(monthAdapter);

        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_dropdown_item);
        spnYear.setAdapter(yearAdapter);

        rdoPayment = findViewById(R.id.rdgPayment);

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit:
                if(this.validateData()){
                    this.createUserAndReply();
                }
                break;
            case R.id.btn_cancel:
                this.openSignInActivity();
        }

    }

    private void createUserAndReply(){
        firstName = edtFirstName.getText().toString();
        lastName = edtLastName.getText().toString();
        phoneNumber = edtPhone.getText().toString();
        email = edtEmail.getText().toString();
        password = edtPass.getText().toString();
        rdoSelected = findViewById(rdoPayment.getCheckedRadioButtonId());
        paymentType = rdoSelected.getText().toString();
        carPlate = edtPlate.getText().toString();
        expDate = (spnMonth.getSelectedItem().toString() + "/" + spnYear.getSelectedItem().toString());
        cardNumber = edtCardNumber.getText().toString();

        User newUser = new User(firstName, lastName, email, password, carPlate, cardName, expDate, phoneNumber, cvv, paymentType, cardNumber);
        Log.d("SignUpActivity", newUser.toString());

        //reply to previous intent
        Intent replyIntent = new Intent();
        //TODO FIXXXX replyIntent.putExtra(EXTRA_REPLY, newUser);
        setResult(RESULT_OK, replyIntent);
        finish();
    }

    private boolean validateData(){
        boolean allValidations = true;

        if (edtFirstName.getText().toString().isEmpty()){
            edtFirstName.setError("You must enter first name");
            allValidations = false;
        }

        if (edtLastName.getText().toString().isEmpty()){
            edtLastName.setError("You must enter last name");
            allValidations = false;
        }

        if (edtPhone.getText().toString().isEmpty()){
            edtPhone.setError("You must provide phone number");
            allValidations = false;
        }

        if(edtExpDate.getText().toString().isEmpty()){
            edtExpDate.setError("Please select expiry date");
            allValidations = false;
        }

        if (edtEmail.getText().toString().isEmpty()){
            edtEmail.setError("Email cannot be empty");
            allValidations = false;
        }else if (!Utils.isValidEmail(edtEmail.getText().toString())){
            edtEmail.setError("Please provide valid email address");
            allValidations = false;
        }

        if (edtPass.getText().toString().isEmpty()){
            edtPass.setError("Please enter password");
            allValidations = false;
        }

        if (edtPlate.getText().toString().isEmpty()){
            edtPlate.setError("Please enter plate number");
            allValidations = false;
        }

        if (edtCVV.getText().toString().isEmpty()){
            edtCVV.setError("Please enter CVV.");
            allValidations = false;
        }

        if (edtCardName.getText().toString().isEmpty()){
            edtCardName.setError("Please enter card name.");
            allValidations = false;
        }

        if (edtConfirmPass.getText().toString().isEmpty()){
            edtConfirmPass.setError("You must enter confirm password");
            allValidations = false;
        }else if (!edtPass.getText().toString().equals(edtConfirmPass.getText().toString())){
            edtConfirmPass.setError("Both passwords must be same");
            allValidations = false;
        }

        if (rdoSelected.getText().toString().isEmpty()){
            rdoSelected.setError("You must select a payment type");
            allValidations = false;
        }

        return allValidations;
    }

    void openSignInActivity(){
        Intent mainIntent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(mainIntent);
    }
}
