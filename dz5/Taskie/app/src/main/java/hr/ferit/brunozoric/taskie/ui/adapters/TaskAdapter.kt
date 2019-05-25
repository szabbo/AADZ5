package hr.ferit.brunozoric.taskie.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import hr.ferit.brunozoric.taskie.R
import hr.ferit.brunozoric.taskie.model.Task

class TaskAdapter(private val onItemSelected: (Task) -> Unit) : Adapter<TaskHolder>() {

    private val tasks: MutableList<Task> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskHolder(v)
    }

    override fun getItemCount() = tasks.size

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bindData(tasks[position], onItemSelected)
    }

    fun setTasks(task: MutableList<Task>) {
        tasks.clear()
        tasks.addAll(task)
        notifyDataSetChanged()
    }

    fun deleteTasks() {
        tasks.clear()
    }

    fun deleteTask(viewHolder: RecyclerView.ViewHolder): Long? {
        val taskId = tasks[viewHolder.adapterPosition].taskId
        tasks.removeAt(viewHolder.adapterPosition)
        notifyItemRemoved(viewHolder.adapterPosition)

        return taskId
    }
}





