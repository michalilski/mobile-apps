package com.example.todoapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import java.text.SimpleDateFormat
import com.example.todoapp.Config

class RecyclerAdapter(private val dataSet: ArrayList<Task>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val dt = SimpleDateFormat(Config.DATE_FORMAT)
    private val priorityMap : Map<Int, String> = mapOf(
            0 to Config.LOW_PRIORITY,
            1 to Config.MEDIUM_PRIORITY,
            2 to Config.HIGH_PRIORITY,
    )
    var onItemClick: ((Task) -> Unit)? = null


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val taskTitleTextView: TextView = view.findViewById(R.id.taskTitleTextView)
        val taskDateTextView: TextView = view.findViewById(R.id.taskDateTextView)
        val taskNoteTextView: TextView = view.findViewById(R.id.taskNoteTextView)
        val taskPriorityTextView: TextView = view.findViewById(R.id.taskPriorityTextView)
        val taskIconImageView: ImageView = view.findViewById(R.id.taskIconImageView)

        init {
            view.setOnClickListener{
                onItemClick?.invoke(dataSet[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_adapter, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.taskTitleTextView.text = dataSet[position].title
        viewHolder.taskDateTextView.text = dt.format(dataSet[position].date.time)
        viewHolder.taskNoteTextView.text = dataSet[position].note
        viewHolder.taskPriorityTextView.text = priorityMap[dataSet[position].priority]
//        TODO
//        viewHolder.taskIconImageView.setImageResource()
    }

    override fun getItemCount() = dataSet.size

    fun addTask(task: Task){
        dataSet.add(task)
        notifyItemInserted(dataSet.size-1)
    }

}
