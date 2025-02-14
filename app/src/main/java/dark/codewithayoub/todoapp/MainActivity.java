package dark.codewithayoub.todoapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import dark.codewithayoub.todoapp.databinding.ActivityMainBinding;
import dark.codewithayoub.todoapp.db.TaskViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private TaskViewModel db;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fabAddTask.setOnClickListener(view -> {
            startActivity(new Intent(this, AddTaskActivity.class));
        });
        db = new ViewModelProvider(this).
                get(TaskViewModel.class);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setHasFixedSize(true);
        adapter = new TaskAdapter(new ArrayList<>(),getApplication());
        binding.recyclerView.setAdapter(adapter);
        db.getAllTasks().observe(this, tasks -> {
            adapter.setTasks(tasks);
        });
    }
}