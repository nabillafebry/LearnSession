package com.feby.asyst.session;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.feby.asyst.session.adapter.TaskAdapter;
import com.feby.asyst.session.model.Login;
import com.feby.asyst.session.model.Task;
import com.feby.asyst.session.retrofit.ApiClient;
import com.feby.asyst.session.retrofit.ApiService;
import com.feby.asyst.session.retrofit.request.TaskRequest;
import com.feby.asyst.session.retrofit.response.TaskResponse;
import com.feby.asyst.session.utility.SessionUtil;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TaskAdapter taskAdapter;
    ArrayList<Task> listTask = new ArrayList<>();
    boolean isLoading=false;
    SessionUtil sessionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar =findViewById(R.id.progressbar);

        sessionUtil = new SessionUtil(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        taskAdapter = new TaskAdapter(this, listTask, new TaskAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Task task) {
                Toast.makeText(getApplicationContext(), task.getCustomer_name(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(taskAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(1)){
                    if (!isLoading){
                        progressBar.setVisibility(View.VISIBLE);
                        isLoading=true;
                        getTask();
                    }
                }
            }
        });

        getTask();
    }

    public void getTask(){
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setMethod("getAllTask");

        Login param = new Login();
        param.setUsername(sessionUtil.loadUsername());
        taskRequest.setParam(param);

        Call<TaskResponse> call = apiService.getTask(taskRequest);

        call.enqueue(new Callback<TaskResponse>() {
            @Override
            public void onResponse(Call<TaskResponse> call, Response<TaskResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                if (response.body()!=null){
                    if (response.body().getData().size()>0) {
                        listTask.addAll(response.body().getData());
                        taskAdapter.notifyDataSetChanged();
                        isLoading = false;
                    }
                }
            }

            @Override
            public void onFailure(Call<TaskResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
                t.printStackTrace();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.exit_menu:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirmation").setCancelable(false).setMessage("Are You Sure?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sessionUtil.saveUsername("");
                        sessionUtil.savePassword("");
                        Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }).setNegativeButton("No", null).show();

            break;
        }
        return super.onOptionsItemSelected(item);
    }
}
