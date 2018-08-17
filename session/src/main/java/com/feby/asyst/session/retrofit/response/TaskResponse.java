package com.feby.asyst.session.retrofit.response;

import com.feby.asyst.session.model.Task;

import java.util.ArrayList;

public class TaskResponse {
    String status, message;
    ArrayList<Task> data;

    public ArrayList<Task> getData() {
        return data;
    }

    public void setData(ArrayList<Task> data) {
        this.data = data;
    }


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

}
