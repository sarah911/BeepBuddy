package com.example.beepbuddy.model;

import java.io.Serializable;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
@Entity(tableName = "user_table")
public class User implements Serializable {

    @PrimaryKey
    @NonNull
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String plateNumber;

    private String cardName;

    private String expDate;

    private String cvv;

    private String paymentType;

    private String cardNumber;

    public User(String firstName, String lastName, String email, String password,
                String plateNumber, String cardName, String expDate,String phoneNumber,
                String cvv, String paymentType, String cardNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.plateNumber = plateNumber;
        this.cardName = cardName;
        this.expDate = expDate;
        this.cvv = cvv;
        this.paymentType = paymentType;
        this.cardNumber = cardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getCardNumber() { return cardNumber; }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


//    public void calculateParkingCharges(){
//
//    }
    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", cardName'" + cardName + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate=" + expDate +
                '}';
    }
}
