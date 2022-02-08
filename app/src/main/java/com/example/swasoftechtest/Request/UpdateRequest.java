package com.example.swasoftechtest.Request;

public class UpdateRequest {
String id,email,username,mobile,address,state,city,image;

    public UpdateRequest(String id,String email, String username, String mobile, String address, String state, String city, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.state = state;
        this.city = city;
        this.image = image;
    }

    public UpdateRequest(String id, String email, String username, String mobile) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.mobile = mobile;
    }
    public UpdateRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
