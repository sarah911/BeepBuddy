package com.example.beepbuddy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
                //TODO
                break;
            case R.id.item_profile:
                //TODO
                break;
            case R.id.item_manual:
                //TODO
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



}
