package com.example.beepbuddy;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.example.beepbuddy.AddActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * BeepBuddy created by singla.pallavi12
 * student ID : 991524414
 * on 2019-11-28
 */
public class UserAdapter extends CursorAdapter
{
    public UserAdapter(Context context, Cursor cursor)
    {
        super(context, cursor);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        return LayoutInflater.from(context).inflate(R.layout.activity_list_view, parent, false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor)
    {

        TextView tv1CarPlateNumber = view.findViewById(R.id.tv1CarPlateNumber);

        TextView tv1ParkingDuration = view.findViewById(R.id.tv1ParkingDuration);
        TextView tv1BuildingCode = view.findViewById(R.id.tv1BuildingCode);
        TextView tv1HostSuite = view.findViewById(R.id.tv1HostSuite);
        TextView tv1Date = view.findViewById(R.id.tv1Date);
        TextView tv1Time = view.findViewById(R.id.tv1Time);
        TextView tv1ParkingCost = view.findViewById(R.id.tv1ParkingCost);

        String v1 = cursor.getString(1);
        String v2 = cursor.getString(2);
        String v3 = cursor.getString(3);
        String v4 = cursor.getString(4);
        String v5 = cursor.getString(5);
        String v6 = cursor.getString(6);
        String v7 = cursor.getString(7);


        tv1CarPlateNumber.setText(v1);

        tv1ParkingDuration.setText(v2);
        tv1BuildingCode.setText(v3);
        tv1HostSuite.setText(v4);
        tv1Date.setText(v5);
        tv1Time.setText(v6);
        tv1ParkingCost.setText(v7);

    }

}