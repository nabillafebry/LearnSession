package com.feby.asyst.session;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.feby.asyst.session.model.Login;
import com.feby.asyst.session.retrofit.ApiClient;
import com.feby.asyst.session.retrofit.ApiService;
import com.feby.asyst.session.retrofit.request.LoginRequest;
import com.feby.asyst.session.retrofit.response.LoginResponse;
import com.feby.asyst.session.utility.SessionUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUsername, etPassword;
    Button btnLogin;
    SessionUtil sessionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username_edittext);
        etPassword = findViewById(R.id.password_edittext);
        btnLogin = findViewById(R.id.login_button);

        btnLogin.setOnClickListener(this);

        sessionUtil = new SessionUtil(this);

        checkLogin();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if (TextUtils.isEmpty(etUsername.getText().toString())){
                    etUsername.setError("Required");
                } else if (TextUtils.isEmpty(etPassword.getText().toString())){
                    etPassword.setError("Required");
                }
                else {

                    getLoginData();
                }
                break;
        }
    }

    public void getLoginData(){
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest();
        Call<LoginResponse> call = apiService.getLogin(loginRequest);

        final Login login = new Login();
        login.setUsername(etUsername.getText().toString());
        login.setPassword(etPassword.getText().toString());

        loginRequest.setMethod("getProfileInfo");
        loginRequest.setParam(login);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                sessionUtil.saveUsername(login.getUsername());
                sessionUtil.savePassword(login.getPassword());
                Toast.makeText(getApplicationContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Login Gagal!", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    private void checkLogin(){
        if (!sessionUtil.loadUsername().equalsIgnoreCase("")){
            Intent intent = new Intent(MainActivity.this, TaskActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
