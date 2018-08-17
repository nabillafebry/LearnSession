package com.feby.asyst.session;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText etUsername, etPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.username_edittext);
        etPassword = findViewById(R.id.password_edittext);
        btnLogin = findViewById(R.id.login_button);

        btnLogin.setOnClickListener(this);

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

                }
                break;
        }
    }

    public void getLoginData(){
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);
        LoginRequest loginRequest = new LoginRequest();
        Call<LoginResponse> call = apiService.getLogin(loginRequest);

        Login param = new Login();
        param.setUsername(etUsername.getText().toString());
        param.setPassword(etPassword.getText().toString());

        loginRequest.setMethod("getProfileInfo");
        loginRequest.setParam(param);
    }
}
