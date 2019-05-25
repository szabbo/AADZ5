package hr.ferit.brunozoric.taskie.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import hr.ferit.brunozoric.taskie.model.Task

@Database(entities = [Task::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        private var instance: TaskDatabase? = null
        fun getInstance(context: Context): TaskDatabase {
            if (instance == null)
                instance =
                    Room.databaseBuilder(context.applicationContext, TaskDatabase::class.java, "TaskDatabase")
                        .allowMainThreadQueries().build()

            return instance as TaskDatabase
        }
    }
}