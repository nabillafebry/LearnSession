package com.feby.asyst.session.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.feby.asyst.session.R;
import com.feby.asyst.session.model.Task;
import com.feby.asyst.session.utility.DateUtils;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder>{


    public interface OnItemClickListener{
        void onItemClick(Task task);
    }


    Context mContext;
    ArrayList<Task> mListTask;
    OnItemClickListener listener;

    public TaskAdapter(Context mContext, ArrayList<Task> mListTask, OnItemClickListener listener) {
        this.mContext = mContext;
        this.mListTask = mListTask;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTaskID, tvCustomerID, tvCustomerName, tvCustomerAddress, tvStartDate, tvFinishDate;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvTaskID = itemView.findViewById(R.id.task_id_textview);
            tvCustomerID = itemView.findViewById(R.id.customer_id_textview);
            tvCustomerName = itemView.findViewById(R.id.customer_name_textview);
            tvCustomerAddress = itemView.findViewById(R.id.customer_address_textview);
            tvStartDate = itemView.findViewById(R.id.start_date_textview);
            tvFinishDate = itemView.findViewById(R.id.finish_date_textview);


            cardView = itemView.findViewById(R.id.cardview);



        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemVI = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);

        return new TaskAdapter.MyViewHolder(itemVI);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Task task = mListTask.get(position);

        holder.tvCustomerName.setText(task.getCustomer_name());
        holder.tvCustomerAddress.setText(task.getCustomer_address());
        holder.tvCustomerID.setText("#"+task.getCustomer_id());
        holder.tvTaskID.setText(task.getTask_id());
        holder.tvStartDate.setText(DateUtils.formatDate("yyyy-MM-dd", "EEEE, dd MMMM yyyy", task.getStartDate()));
        if (task.getFinishDate() != null){
            holder.tvFinishDate.setVisibility(View.VISIBLE);
            holder.tvFinishDate.setText("Selesai pada "+ DateUtils.formatDate("yyyy-MM-dd ssss", "dd MMM yyyy",task.getFinishDate()));
        }
        else {
            holder.tvFinishDate.setVisibility(View.GONE);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListTask.size();
    }

}
