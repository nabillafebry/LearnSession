package com.feby.asyst.session.retrofit.request;

import com.feby.asyst.session.model.Task;

public class TaskRequest {
    String method;
    Task param;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Task getParam() {
        return param;
    }

    public void setParam(Task param) {
        this.param = param;
    }
}
