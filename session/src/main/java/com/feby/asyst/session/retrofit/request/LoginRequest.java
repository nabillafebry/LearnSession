package com.feby.asyst.session.retrofit.request;

import com.feby.asyst.session.model.User;

public class LoginRequest {
    String method;
    User param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public User getParam() {
        return param;
    }

    public void setParam(User param) {
        this.param = param;
    }
}
