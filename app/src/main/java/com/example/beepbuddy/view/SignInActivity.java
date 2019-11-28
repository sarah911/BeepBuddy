package com.example.beepbuddy.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.beepbuddy.R;
import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;

import java.util.List;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtEmail;
    EditText edtPassword;
    Button btnSignIn;
    Switch swtRemember;
    TextView txtSignUp;
    TextView txtForgetPass;

    String email;
    String password ;

    public static final int SIGN_UP_REQUEST_CODE = 1;
    public static final String USER_PREF = "com.example.beepbuddy.userprefer";
    public static final String EMAIL = "EMAIL";
    public static final String PASSWORD = "PASSWORD";

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        this.referWidgets();

        userViewModel = new UserViewModel(getApplication());

        userViewModel.getAllUsers().observe(SignInActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users){
                    Log.e("SignInActivity", user.toString());
                }
            }
        });

        this.getRememberedData();
    }



    void referWidgets(){
        edtEmail= findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(this);

        txtSignUp = findViewById(R.id.txtSignUp);
        txtSignUp.setOnClickListener(this);

        swtRemember = findViewById(R.id.swtRemember);

        txtForgetPass = findViewById(R.id.txtForgetPassword);
        txtForgetPass.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSignIn:
                //sign in operation
                this.signIn();
                break;
            case R.id.txtForgetPassword:
                //forget password
                this.resetPassword();
                break;
            case R.id.txtSignUp:
                //sign up operation
                this.signUp();
        }
    }

    void signIn() {
        email = edtEmail.getText().toString();
        password = edtPassword.getText().toString();

        if (this.authenticateUser(email, password)){
            if(swtRemember.isChecked()){
                this.rememberData();
            }else{
                this.forgetData();
            }
            //login successful
            Toast.makeText(this, "Login successful",Toast.LENGTH_LONG).show();
            this.openMainActivity();
        }else{
            //login unsuccessful
            Toast.makeText(this, "Incorrect Email/Password! Try again.",Toast.LENGTH_LONG).show();
        }
    }


    void signUp(){
        Intent signUpIntent = new Intent(SignInActivity.this, SignUpActivity.class);
        startActivityForResult(signUpIntent, SIGN_UP_REQUEST_CODE);
    }

    void resetPassword(){
        Intent passIntent = new Intent(SignInActivity.this, ForgetPassActivity.class);
        startActivity(passIntent);
    }

    void openMainActivity(){
        Intent mainIntent = new Intent(SignInActivity.this, MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_UP_REQUEST_CODE){
            if(resultCode == RESULT_OK){

                User newUser = (User) data.getSerializableExtra("com.example.beepbuddy.REPLY");
                Log.e("SIGN_IN_ACTIVITY", newUser.toString());

                //insert new user account detail into database
                userViewModel.insert(newUser);
            }
        }
    }

    private boolean authenticateUser(String email, String password){
        List<User> allUsers = userViewModel.getAllUsers().getValue();

        for (User user: allUsers){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    private void rememberData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        sp.edit().putString(EMAIL, edtEmail.getText().toString()).commit();
        sp.edit().putString(PASSWORD, edtPassword.getText().toString()).commit();
    }

    private void forgetData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);
        //to clear all preferences
        sp.edit().clear().commit();

    }

    private void getRememberedData(){
        SharedPreferences sp = getSharedPreferences(USER_PREF, Context.MODE_PRIVATE);

        edtEmail.setText(sp.getString(EMAIL, ""));
        edtPassword.setText(sp.getString(PASSWORD, ""));
    }

}