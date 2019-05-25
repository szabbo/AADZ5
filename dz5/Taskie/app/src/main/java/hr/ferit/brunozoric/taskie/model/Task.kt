package hr.ferit.brunozoric.taskie.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import hr.ferit.brunozoric.taskie.database.Converter

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var taskId: Long? = null,
    var id: Int = 0,
    val title: String,
    val description: String,
    @TypeConverters(Converter::class)
    val priority: Priority
)