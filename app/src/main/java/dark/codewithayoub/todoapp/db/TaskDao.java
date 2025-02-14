package dark.codewithayoub.todoapp.db;

import androidx.lifecycle.LiveData;
import androidx.loader.content.AsyncTaskLoader;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);
    @Update
    void updateTask(Task task);
    @Delete
    void deleteTask(Task task);
    @Query("select * from Task order by name asc")
    LiveData<List<Task>> getAllTasks();
}
