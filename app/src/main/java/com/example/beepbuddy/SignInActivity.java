package com.example.beepbuddy;

import java.util.Date;

/**
 * BeepBuddy created by caitlinrush
 * student ID : 991534296
 * on 2019-11-24
 */
public class SignInActivity {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phoneNumber;

    private String plateNumber;

    private String cardName;

    private Date expDate;

    private String cvv;

    private String paymentType;

    public SignInActivity(String firstName, String lastName, String email,
                          String password, String phoneNumber, String plateNumber,
                          String cardName, Date expDate, String cvv, String paymentType) {
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
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
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

    @Override
    public String toString(){
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", plateNumber='" + plateNumber + '\'' +
                ", cardName'" + cardName + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", cvv='" + cvv + '\'' +
                ", expDate=" + expDate +
                '}';
    }
}

