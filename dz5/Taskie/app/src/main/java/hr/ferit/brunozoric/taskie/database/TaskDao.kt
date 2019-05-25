package hr.ferit.brunozoric.taskie.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getTasks(): MutableList<Task>

    @Query("UPDATE Task SET title = :newTitle, description = :newDescription, priority = :newPriority WHERE taskId = :taskId")
    fun editTask(taskId: Long?, newTitle: String, newDescription: String, newPriority: Priority)

    @Insert(onConflict = IGNORE)
    fun insertNewTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM Task")
    fun deleteTasks()
}