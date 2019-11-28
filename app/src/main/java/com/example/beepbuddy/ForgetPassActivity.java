package com.example.beepbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.beepbuddy.model.User;
import com.example.beepbuddy.viewmodel.UserViewModel;

import java.util.List;

public class ForgetPassActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editEmail;
    EditText editPlate;
    Button btnSubmit;
    TextView tvPass;

    String email;
    String plate;


    UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        this.referWidgets();

        userViewModel = new UserViewModel(getApplication());

        userViewModel.getAllUsers().observe(ForgetPassActivity.this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //task when the data changes
                for (User user : users){
                    Log.e("SignInActivity", user.toString());
                }
            }
        });

    }


    void referWidgets(){
        editEmail= findViewById(R.id.editEmailFP);
        editPlate = findViewById(R.id.editPlateFP);
        btnSubmit = findViewById(R.id.btnSubmitFP);
        btnSubmit.setOnClickListener(this);
        tvPass = findViewById(R.id.tvPassFP);
    }

    @Override
    public void onClick(View view) {
        email = editEmail.getText().toString();
        plate = editPlate.getText().toString();
        this.authenticateUser(email, plate);
    }

    private boolean authenticateUser(String email, String plate){
        List<User> allUsers = userViewModel.getAllUsers().getValue();

        for (User user: allUsers){
            if (user.getEmail().equals(email) && user.getPlateNumber().equals(plate)){
                tvPass.setText(user.getPassword());
                return true;
            }
        }
        return false;
    }
}
