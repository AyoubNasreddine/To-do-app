package dark.codewithayoub.todoapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Database(entities = {Task.class}, version = 2)
public abstract class TaskDataBase extends RoomDatabase {
    private static volatile TaskDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public abstract TaskDao taskDao();
    public static TaskDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    TaskDataBase.class, "tasks_db"
                            ).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    public static void execute(Runnable task) {
        databaseWriteExecutor.execute(task);
    }
}
