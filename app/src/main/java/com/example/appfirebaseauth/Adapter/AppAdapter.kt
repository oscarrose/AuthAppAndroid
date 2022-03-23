package com.example.appfirebaseauth.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.appfirebaseauth.databinding.TaskListItemsBinding
import com.example.appfirebaseauth.fragments.TaskListFragmentDirections
import com.example.appfirebaseauth.model.Task

class AppAdapter(/*private val task:List<Task>*/):RecyclerView.Adapter<AppAdapter.ViewHolder>()
{

    private var listTask= emptyList<Task>()

    class ViewHolder(binding: TaskListItemsBinding):RecyclerView.ViewHolder(binding.root){
        val taskTitle=binding.taskTitleView
        val taskDate=binding.dateTask
        val noteTask=binding.notetask
        val itemsLayout=binding.itemLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskListItemsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val taskItems= listTask[position];
        holder.taskTitle.text=taskItems.title.toString()
        holder.taskDate.text=taskItems.dateTask.toString()
        holder.noteTask.text=taskItems.note.toString()

       holder.itemsLayout.setOnClickListener(){
            //val action=TaskListFragmentDirections.actionTaskListFragmentToUpdateTaskFragment(taskItems)
            //holder.itemsLayout.findNavController().navigate(TaskListFragmentDirections.actionTaskListFragmentToUpdateTaskFragment())
        }

    }

    override fun getItemCount(): Int {
       return listTask.size
    }

    //notification
    fun setDatatask(task:List<Task>){
        this.listTask=task
        notifyDataSetChanged()
    }
}