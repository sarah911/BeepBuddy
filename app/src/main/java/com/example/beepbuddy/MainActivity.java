package com.example.beepbuddy;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.beepbuddy.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnMainSearch;
    Button btnMainPark;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMainPark = findViewById(R.id.btn2Park);
        btnMainPark.setOnClickListener(this);

        btnMainSearch = findViewById(R.id.btn2Search);
        btnMainSearch.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item_add:
                this.openAddParking();
                break;
            case R.id.item_receipts:
                this.openReceiptList();
                break;
            case R.id.item_search:
                this.openSearchParking();
                break;
            case R.id.item_profile:
                this.editProfile();
                break;
            case R.id.item_manual:
                Intent intentM = new Intent(this, WebActivity.class);
                this.startActivity(intentM);
                break;
            case R.id.item_support:
                Intent intentS = new Intent(this, SupportActivity.class);
                this.startActivity(intentS);
                break;
            case R.id.item_signout:
                Intent intentE = new Intent(this, SignInActivity.class);
                this.startActivity(intentE);
                break;
            default:
                return super.onOptionsItemSelected(item);

        }
        return true;
    }

    void editProfile(){
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_edit, null);

        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Edit Profile")
                .setMessage("Please update profile details")
                .setView(dialogView)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        EditText editFN = dialogView.findViewById(R.id.editFN);
                        EditText editLN = dialogView.findViewById(R.id.editLN);
                        EditText editPhone = dialogView.findViewById(R.id.edit_phone);
                        EditText editEmail = dialogView.findViewById(R.id.edit_email);
                        EditText editPass = dialogView.findViewById(R.id.edit_password);
                        EditText editPlate = dialogView.findViewById(R.id.edit_plate);
                        EditText editCardName = dialogView.findViewById(R.id.edit_card_name);
                        EditText editCardNum = dialogView.findViewById(R.id.edit_card_number);
                        EditText editCVV = dialogView.findViewById(R.id.edit_cvv);

                        String newFN = editFN.getText().toString();

                        //newFN =


                        //TODO add spinner fields for expiry date

                        //TODO how to update this to the user ?

                        //userViewModel.update(user);


                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn2Park:
                this.openAddParking();
                break;
            case R.id.btn2Search:
                this.openSearchParking();
                break;
        }
    }

    void openAddParking(){
        Intent intent = new Intent(this, AddActivity.class);
        this.startActivity(intent);
    }
    void openSearchParking(){
        Intent intentI = new Intent(this, MapsActivity.class);
        this.startActivity(intentI);
    }

    void openReceiptList(){
        Intent intentR = new Intent(this,ReceiptListActivity.class);
        this.startActivity(intentR);
    }
}
