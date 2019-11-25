package com.example.beepbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                Intent intent = new Intent(this, AddActivity.class);
                this.startActivity(intent);
                break;
            case R.id.item_receipts:
                //TODO
                break;
            case R.id.item_search:
                Intent intentI = new Intent(this, MapsActivity.class);
                this.startActivity(intentI);
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

                        //TODO other fields and add to the xml as well
                        //TODO how to update this to the user ?


                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        alertDialog.show();
    }

}
