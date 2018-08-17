package com.feby.asyst.session.retrofit.request;

import com.feby.asyst.session.model.Login;

public class LoginRequest {
    String method;
    Login param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Login getParam() {
        return param;
    }

    public void setParam(Login param) {
        this.param = param;
    }
}
