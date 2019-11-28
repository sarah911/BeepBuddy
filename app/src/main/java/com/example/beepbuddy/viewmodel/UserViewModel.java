package com.example.beepbuddy.viewmodel;

import android.app.Application;

import com.example.beepbuddy.db.UserRepository;
import com.example.beepbuddy.model.User;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> allUsers;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        allUsers = userRepository.getAllUsers();
    }

    public void insert(User user){
        userRepository.insert(user);
    }
    public void update(User user) {userRepository.update(user);}

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }
}
