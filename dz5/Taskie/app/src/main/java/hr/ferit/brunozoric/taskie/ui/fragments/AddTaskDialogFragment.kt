package hr.ferit.brunozoric.taskie.ui.fragments

import android.os.Bundle
import android.text.TextUtils.isEmpty
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.common.displayToast
import hr.ferit.brunozoric.taskie.model.Priority
import hr.ferit.brunozoric.taskie.model.Task
import hr.ferit.brunozoric.taskie.persistence.Repository
import hr.ferit.brunozoric.taskie.persistence.TaskRoomRepository
import kotlinx.android.synthetic.main.fragment_new_task.*

class AddTaskDialogFragment : DialogFragment() {

    private var taskAddedListener: TaskAddedListener? = null
    private val repository = TaskRoomRepository()

    interface TaskAddedListener {
        fun onTaskAdded(task: Task)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.FragmentDialogTheme)
    }

    fun setTaskAddedListener(listener: TaskAddedListener) {
        taskAddedListener = listener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_new_task, container)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        initListeners()
    }

    private fun initUi() {
        context?.let {
            priority_selector.adapter =
                ArrayAdapter<Priority>(it, android.R.layout.simple_spinner_dropdown_item, Priority.values())
            priority_selector.setSelection(0)
        }
    }

    private fun initListeners() {
        btn_save_task_action.setOnClickListener { saveTask() }
    }

    private fun saveTask() {
        if (isInputEmpty()) {
            context?.displayToast(getString(R.string.emptyFields))
            return
        }

        val title = et_new_task_title_input.text.toString()
        val description = et_new_task_description_input.text.toString()
        val priority = priority_selector.selectedItem as Priority

        val task = Task(title = title, description = description, priority = priority)

        repository.addTask(task)
        clearUi()

        taskAddedListener?.onTaskAdded(task)
        dismiss()
    }

    private fun clearUi() {
        et_new_task_title_input.text.clear()
        et_new_task_description_input.text.clear()
        priority_selector.setSelection(0)
    }

    private fun isInputEmpty(): Boolean =
        isEmpty(et_new_task_title_input.text) || isEmpty(et_new_task_description_input.text)

    companion object {
        fun newInstance(): AddTaskDialogFragment = AddTaskDialogFragment()
    }
}