package dark.codewithayoub.todoapp;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import dark.codewithayoub.todoapp.db.Repository;
import dark.codewithayoub.todoapp.db.Task;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List <Task> tasks;
    private Application application;
    public TaskAdapter(List<Task>tasks,Application application){
        this.tasks=tasks;
        this.application = application;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_task,parent,false);
        return new TaskViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTask.setText(task.getName());
        holder.checkBox.setChecked(task.isDone());
        if (task.isDone()){
            Drawable d = ContextCompat.getDrawable(holder.checkBox.getContext(), R.drawable.curve_done);
            holder.layout.setBackground(d);
        }else{
            Drawable d =ContextCompat.getDrawable(holder.checkBox.getContext(),R.drawable.curve);
            holder.layout.setBackground(d);
        }
    }
    @Override
    public int getItemCount() {
        return tasks.size();
    }
    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
        notifyDataSetChanged();
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView textViewTask;
        private CheckBox checkBox;
        private ConstraintLayout layout;
        private Repository rep;
        private Context context;
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTask= itemView.findViewById(R.id.name_custom);
            checkBox =itemView.findViewById(R.id.checkbox_custom);
            layout = itemView.findViewById(R.id.main_custom);
            context= itemView.getContext();
            itemView.setOnClickListener(this);
            checkBox.setOnClickListener(this);
            rep = new Repository(application);
        }

        @Override
        public void onClick(View view) {
            if (view == checkBox){
                Task task = tasks.get(getAdapterPosition());
                boolean isDone = task.isDone();
                Task newTask = new Task(task.getName(),!isDone);
                newTask.setId(task.getId());
                rep.updateTask(newTask);
            } else {
                Intent i = new Intent(checkBox.getContext(),AddTaskActivity.class);
                Task task  = tasks.get(getAdapterPosition());
                i.putExtra("name",task.getName());
                i.putExtra("isDone",task.isDone());
                i.putExtra("id",task.getId());
                context.startActivity(i);
            }
        }
    }
}
