package com.example.listadetareaspa;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private LinearLayout taskListLayout;
    private EditText editTextTask;
    private Button btnAddTask;
    private Button btnDeleteTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskListLayout = findViewById(R.id.taskListLayout);
        editTextTask = findViewById(R.id.editTextTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        btnDeleteTask = findViewById(R.id.btnDeleteTask);

        Button addButton = btnAddTask; // Almacenar btnAddTask en una variable local

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskText = editTextTask.getText().toString().trim();
                if (!taskText.isEmpty()) {
                    addTaskToList(taskText);
                    editTextTask.setText("");
                }
            }
        });

        btnDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < taskListLayout.getChildCount(); i++) {
                    View view = taskListLayout.getChildAt(i);
                    if (view instanceof LinearLayout) {
                        LinearLayout layout = (LinearLayout) view;
                        TextView textView = (TextView) layout.getChildAt(0);
                        if (textView.isSelected()) {
                            taskListLayout.removeViewAt(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    private void addTaskToList(String taskText) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout layout = new LinearLayout(this);
        layout.setLayoutParams(layoutParams);
        layout.setOrientation(LinearLayout.HORIZONTAL);

        TextView taskTextView = new TextView(this);
        taskTextView.setText(taskText);
        taskTextView.setLayoutParams(layoutParams);
        taskTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
            }
        });
        layout.addView(taskTextView);

        taskListLayout.addView(layout);
    }
}
