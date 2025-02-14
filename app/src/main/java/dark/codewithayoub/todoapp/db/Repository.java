package dark.codewithayoub.todoapp.db;
import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private TaskDao taskDao ;
    public Repository(Application application){
        TaskDataBase db = TaskDataBase.getInstance(application);
        taskDao =db.taskDao();
    }
    public void insertTask(Task task){
        TaskDataBase.execute(()->taskDao.insertTask(task));
    }
    public void updateTask(Task task){
        TaskDataBase.execute(()->taskDao.updateTask(task));
    }
    public void deleteTask(Task task){
        TaskDataBase.execute(()->taskDao.deleteTask(task));
    }
    public LiveData<List<Task>> getAllTasks(){
        return taskDao.getAllTasks();
    };
}
