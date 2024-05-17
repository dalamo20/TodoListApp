package com.dalamo.todolistapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText editTextTask;
    private Button buttonAdd;
    private ListView listViewTasks;
    private TaskAdapter taskAdapter;
    private ArrayList<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        listViewTasks = findViewById(R.id.listViewTasks);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        listViewTasks.setAdapter(taskAdapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskDescription = editTextTask.getText().toString().trim();
                if (!taskDescription.isEmpty()) {
                    Task newTask = new Task(taskDescription);
                    taskList.add(newTask);
                    taskAdapter.notifyDataSetChanged();
                    editTextTask.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void removeTask(int position) {
        taskList.remove(position);
        taskAdapter.notifyDataSetChanged();
    }

    public void updateTask(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Update Task");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_update_task, null, false);
        final EditText input = viewInflated.findViewById(R.id.editTextUpdateTask);
        input.setText(taskList.get(position).getDescription());

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String updatedTaskDescription = input.getText().toString().trim();
                if (!updatedTaskDescription.isEmpty()) {
                    taskList.get(position).setDescription(updatedTaskDescription);
                    taskAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Please enter a task", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }
}
