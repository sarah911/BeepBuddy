package com.example.beepbuddy.db;

import android.app.Application;
import android.os.AsyncTask;

import com.example.beepbuddy.model.User;

import java.util.List;

import androidx.lifecycle.LiveData;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
public class UserRepository {
    private UserDao userDao;
    private LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        UserDB db = UserDB.getInstance(application);
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user){
        new insertAsyncTask(userDao).execute(user);
    }

    public void update(User user){
        new updateAsyncTask(userDao).execute(user);
    }

    public static class updateAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao userDao;

        private updateAsyncTask(UserDao userDao) {this.userDao = userDao;}

        @Override
        protected Void doInBackground(User... users) {
            userDao.update(users[0]);
            return null;
        }

    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private UserDao asyncTaskDao;

        insertAsyncTask(UserDao userDao){
            asyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            asyncTaskDao.insert(users[0]);
            return null;
        }
    }

}
