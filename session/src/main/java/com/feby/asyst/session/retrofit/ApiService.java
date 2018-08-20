package com.feby.asyst.session.retrofit;

import com.feby.asyst.session.retrofit.request.LoginRequest;
import com.feby.asyst.session.retrofit.request.TaskRequest;
import com.feby.asyst.session.retrofit.response.LoginResponse;
import com.feby.asyst.session.retrofit.response.TaskResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @POST("Login/getProfileInfo")
    Call<LoginResponse> getLogin(@Body LoginRequest loginRequest);

    @POST("Task/getAllTask")
    Call<TaskResponse> getTask(@Body TaskRequest taskRequest);


}
