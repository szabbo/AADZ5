package hr.ferit.brunozoric.taskie.database

import androidx.room.TypeConverter
import hr.ferit.brunozoric.taskie.model.Priority

class Converter{
    companion object{
        @TypeConverter
        @JvmStatic
        fun fromPriority(priority: Priority) = priority.ordinal

        @TypeConverter
        @JvmStatic
        fun toPriority(priorityValue: Int) = Priority.values()[priorityValue]
    }
}