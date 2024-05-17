package com.dalamo.todolistapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Task> taskList;
    private LayoutInflater inflater;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.task_item, parent, false);
        }

        TextView textViewTask = convertView.findViewById(R.id.textViewTask);
        Button buttonTaskCompleted = convertView.findViewById(R.id.buttonTaskCompleted);
        Button buttonUpdate = convertView.findViewById(R.id.buttonUpdate);

        final Task task = taskList.get(position);
        textViewTask.setText(task.getDescription());

        buttonTaskCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).removeTask(position);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).updateTask(position);
            }
        });

        return convertView;
    }
}

