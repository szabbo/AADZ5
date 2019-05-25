package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.model.Task

interface TaskRepository {
    fun addTask(task: Task)
    fun addTasks(mutableList: MutableList<Task>)
    fun getTask(taskId: Long): Task
    fun getTasks(): List<Task>
    fun editTask(taskId: Long, task: Task)
    fun deleteTask(taskId: Long)
    fun deleteTasks()
}