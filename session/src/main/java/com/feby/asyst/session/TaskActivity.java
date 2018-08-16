package com.feby.asyst.session;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.feby.asyst.session.adapter.TaskAdapter;
import com.feby.asyst.session.model.Task;

import java.util.ArrayList;

public class TaskActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TaskAdapter taskAdapter;
    ArrayList<Task> listTask = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        recyclerView = findViewById(R.id.recyclerview);
        progressBar =findViewById(R.id.progressbar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
