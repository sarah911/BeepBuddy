package com.example.beepbuddy;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.example.beepbuddy.model.User;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;

/**
 * BeepBuddy created by singla.pallavi12
 * student ID : 991524414
 * on 2019-11-28
 */
public class UserAdapter extends ArrayAdapter {

    Context context;
    private List<User> users = new ArrayList<>();

    public UserAdapter(@NonNull Context context, ArrayList<User> users) {
        super(context, 0, users);
        this.context = context;
    }



}
