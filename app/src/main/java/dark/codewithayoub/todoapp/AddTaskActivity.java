package dark.codewithayoub.todoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import dark.codewithayoub.todoapp.databinding.ActivityAddTaskBinding;
import dark.codewithayoub.todoapp.db.Task;
import dark.codewithayoub.todoapp.db.TaskViewModel;

public class AddTaskActivity extends AppCompatActivity {
    private ActivityAddTaskBinding binding ;
    private TaskViewModel db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddTaskBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        String name_task;
        boolean isDone_task;
        int id_task;
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            binding.delBtn.setVisibility(View.VISIBLE);
            name_task=bundle.getString("name");
            isDone_task = bundle.getBoolean("isDone");
            id_task = bundle.getInt("id");
            binding.taskName.setText(name_task);
        } else {
            id_task = -1;
            name_task = "";
            isDone_task=false;
        }
        db = new ViewModelProvider(this)
                .get(TaskViewModel.class);
        binding.doneBtn.setOnClickListener(view -> {
            String name = binding.taskName.getText().toString();
            Task task = new Task(name,false);
            db.insertTask(task);
            Toast.makeText(this, "Task Added !", Toast.LENGTH_SHORT).show();
            finish();
        });
        binding.delBtn.setOnClickListener(view ->{
            if (id_task!=-1){
                Task task = new Task(name_task,isDone_task);
                task.setId(id_task);
                db.deleteTask(task);
                finish();
            }
        });
    }
}