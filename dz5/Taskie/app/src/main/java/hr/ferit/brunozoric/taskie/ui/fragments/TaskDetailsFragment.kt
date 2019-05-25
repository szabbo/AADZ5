package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.view.View
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.EXTRA_TASK_ID
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.Repository
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import hr.ferit.brunozoric.taskie.ui.fragments.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_task.*
import kotlinx.android.synthetic.main.fragment_task_details.*

class TaskDetailsFragment : BaseFragment() {

    private val repository = TaskRoomRepository()
    private var taskID = NO_TASK

    override fun getLayoutResourceId(): Int = R.layout.fragment_task_details

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(EXTRA_TASK_ID)?.let { taskID = it }
        tryDisplayTask(taskID)
        btn_save_task.setOnClickListener { editTask(taskID.toLong()) }
    }

    private fun editTask(taskId: Long) {
        val title = et_edit_task_title.text.toString()
        val description = et_edit_task_description.text.toString()

        when (spinner_edit_priority.selectedItem) {
            "Low" -> {
                val taskEdit = Task(title = title, description = description, priority = Priority.LOW)
                repository.editTask(taskId, taskEdit)
            }

            "Medium" -> {
                val taskEdit = Task(title = title, description = description, priority = Priority.MEDIUM)
                repository.editTask(taskId, taskEdit)
            }

            else -> {
                val taskEdit = Task(title = title, description = description, priority = Priority.HIGH)
                repository.editTask(taskId, taskEdit)
            }
        }
        clearUi()
    }

    private fun clearUi() {
        et_edit_task_title.text.clear()
        et_edit_task_description.text.clear()
    }

    private fun tryDisplayTask(id: Int) {
        try {
            val task = repository.getTask(id.toLong())
            displayTask(task)
        } catch (e: NoSuchElementException) {
            context?.displayToast(getString(R.string.noTaskFound))
        }
    }

    private fun displayTask(task: Task) {
        tv_details_task_title.text = task.title
        tv_details_task_description.text = task.description
        img_details_priority_view.setBackgroundResource(task.priority.getColor())
    }

    companion object {
        const val NO_TASK = -1

        fun newInstance(taskId: Int): TaskDetailsFragment {
            val bundle = Bundle().apply { putInt(EXTRA_TASK_ID, taskId) }
            return TaskDetailsFragment().apply { arguments = bundle }
        }
    }
}
