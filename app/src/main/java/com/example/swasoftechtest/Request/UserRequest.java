package com.example.swasoftechtest.Request;

public class UserRequest {
    String type,mobile,password;

    public UserRequest(String type, String mobile, String password) {
        this.type = type;
        this.mobile = mobile;
        this.password = password;
    }

    public UserRequest() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
