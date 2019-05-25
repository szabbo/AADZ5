package hr.ferit.brunozoric.taskie.ui.activities

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.EXTRA_SCREEN_TYPE
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.activities.base.BaseActivity
import hr.ferit.brunozoric.taskie.ui.adapters.TaskAdapter
import hr.ferit.brunozoric.taskie.ui.fragments.AboutFragment
import hr.ferit.brunozoric.taskie.ui.fragments.TasksFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val adapter by lazy { TaskAdapter { onTaskSelected(it) } }
    private val repository = TaskRoomRepository()
    var tasks = repository.getTasks()

    override fun getLayoutResourceId() = R.layout.activity_main

    override fun setUpUi() {
        showFragment(TasksFragment.newInstance())
        setUpOnNavigationItemSelectedListeners()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_sort_by, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.priority_sort -> {
                tasks = repository.getTasks()
                tasks.sortByDescending { it.priority }
                repository.deleteTasks()
                for (newTask in tasks) {
                    newTask.taskId = null
                    repository.addTask(newTask)
                }
                showFragment(TasksFragment.newInstance())
            }

            R.id.delete_all -> {
                repository.deleteTasks()
                adapter.deleteTasks()
                showFragment(TasksFragment.newInstance())
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onTaskSelected(task: Task) {
        val activityIntent = Intent(this, ContainerActivity::class.java).apply {
            putExtra(EXTRA_SCREEN_TYPE, ContainerActivity.SCREEN_TASK_DETAILS)
            putExtra(EXTRA_TASK_ID, task.taskId?.toInt())
        }
        startActivity(activityIntent)
    }

    private fun setUpOnNavigationItemSelectedListeners() {
        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_tasks -> {
                    showFragment(TasksFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.action_about -> {
                    showFragment(AboutFragment.newInstance())
                    return@setOnNavigationItemSelectedListener true
                }

                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
}