package com.example.swasoftechtest.Request;

public class RegisterRequest {



    String type,username, mobile, password, city, state,contry,email,category;

    public RegisterRequest(String username, String email, String mobile, String password, String city, String state, String contry,String type) {
        this.type = type;
        this.username = username;
        this.mobile = mobile;
        this.password = password;
        this.city = city;
        this.state = state;
        this.contry = contry;
        this.email = email;
    }

    public RegisterRequest(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RegisterRequest(String mobile, String password, String type) {
       this.type=type;
       this.mobile=mobile;
       this.password=password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getemail() {
        return email;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getContry() {
        return contry;
    }

    public void setContry(String contry) {
        this.contry = contry;
    }
}
