package com.example.beepbuddy.db;

import android.content.Context;

import com.example.beepbuddy.model.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
@Database(entities = {User.class}, version = 2, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class UserDB extends RoomDatabase {

    public abstract UserDao userDao();

    private static volatile UserDB INSTANCE;

    public static UserDB getInstance(final Context context){
        if (INSTANCE == null){
            synchronized (UserDB.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        UserDB.class, "beep_buddy_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
        return INSTANCE;
    }



}
