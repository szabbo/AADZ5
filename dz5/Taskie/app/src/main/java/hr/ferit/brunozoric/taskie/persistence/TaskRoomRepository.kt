package hr.ferit.brunozoric.taskie.persistence

import hr.ferit.brunozoric.taskie.Taskie
import hr.ferit.brunozoric.taskie.database.TaskDao
import hr.ferit.brunozoric.taskie.database.TaskDatabase
import hr.ferit.brunozoric.taskie.model.Task

class TaskRoomRepository : TaskRepository {
    var database: TaskDatabase = TaskDatabase.getInstance(Taskie.getAppContext())
    var taskDao: TaskDao = database.taskDao()

    override fun addTask(task: Task) {
        taskDao.insertNewTask(task)
    }

    override fun addTasks(mutableList: MutableList<Task>) {
        mutableList.forEach { addTask(it) }
    }

    override fun getTask(taskId: Long): Task {
        val tasks = taskDao.getTasks()
        tasks.forEach {
            when (taskId) {
                it.taskId -> return it
            }
        }
        return tasks[0]
    }

    override fun getTasks(): MutableList<Task> = taskDao.getTasks()

    override fun editTask(taskId: Long, task: Task) {
        taskDao.editTask(taskId, task.title, task.description, task.priority)
    }

    override fun deleteTask(taskId: Long) {
        val tasks = taskDao.getTasks()
        tasks.forEach {
            when (taskId) {
                it.taskId -> taskDao.deleteTask(it)
            }
        }
    }

    override fun deleteTasks() {
        taskDao.deleteTasks()
    }
}