package com.android.Law.models;

public class UserAccount {
    public String fullName;
    public String phone;
    public String email;
    public String password;
    public Boolean login;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public UserAccount(String fullName, String phone, String password) {
        this.fullName = fullName;
        this.phone = phone;
        this.password = password;
        this.login = false;
    }

    public UserAccount(String fullName, String phone, String email, String password, Boolean login) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.login = login;
    }
}
