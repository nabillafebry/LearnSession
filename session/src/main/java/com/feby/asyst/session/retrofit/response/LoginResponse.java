package com.feby.asyst.session.retrofit.response;

import com.feby.asyst.session.model.Login;

public class LoginResponse {
    String status, message;
    Login data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Login getData() {
        return data;
    }

    public void setData(Login data) {
        this.data = data;
    }
}
