package dark.codewithayoub.todoapp.db;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private Repository rep ;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        rep = new Repository(application);
    }
    public void insertTask(Task task){
        rep.insertTask(task);
    }
    public void updateTask(Task task){
        rep.updateTask(task);
    }
    public void deleteTask(Task task){
        rep.deleteTask(task);
    }
    public LiveData<List<Task>> getAllTasks(){
        return rep.getAllTasks();
    };
}
