package com.example.todoapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Config
import com.example.todoapp.R
import com.example.todoapp.Utils
import com.example.todoapp.activities.MainActivity
import com.example.todoapp.tasks.Task
import java.text.SimpleDateFormat

class RecyclerAdapter(private val dataSet: ArrayList<Task>) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val dt = SimpleDateFormat(Config.DATE_FORMAT)
    private lateinit var activity: Activity


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitleTextView: TextView = view.findViewById(R.id.taskTitleTextView)
        val taskDateTextView: TextView = view.findViewById(R.id.taskDateTextView)
        val taskNoteTextView: TextView = view.findViewById(R.id.taskNoteTextView)
        val taskPriorityTextView: TextView = view.findViewById(R.id.taskPriorityTextView)
        val taskIconImageView: ImageView = view.findViewById(R.id.taskIconImageView)

        init {
            view.setOnClickListener {
                infoAlert(adapterPosition)
            }
            view.setOnLongClickListener{
                deleteAlert(adapterPosition)
                true
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
        viewHolder.taskPriorityTextView.text = Utils.priorityMap[dataSet[position].priority]
//        TODO
//        viewHolder.taskIconImageView.setImageResource()
    }

    override fun getItemCount() = dataSet.size

    fun setActivity(activity: Activity){
        this.activity = activity
    }

    fun addTask(task: Task) {
        dataSet.add(task)
        notifyItemInserted(dataSet.size - 1)
    }

    fun deleteTask(taskId: Int) {
        dataSet.removeAt(taskId)
        notifyItemRemoved(taskId)
    }

    fun deleteAlert(adapterPosition: Int) {
        val adb = AlertDialog.Builder(activity)
        adb.setTitle("Do you want to delete ${dataSet[adapterPosition].title}?")
        adb.setIcon(R.drawable.trash_icon)
        adb.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            deleteTask(adapterPosition)
        }
        adb.setNegativeButton("No") { _: DialogInterface, _: Int -> }
        adb.show()
    }

    fun infoAlert(adapterPosition: Int) {
        val adb = AlertDialog.Builder(activity)
        adb.setTitle(dataSet[adapterPosition].title)
        //TODO set task icon
        adb.setIcon(R.drawable.trash_icon)
        adb.setMessage("Note:\n" + dataSet[adapterPosition].note)
        adb.setPositiveButton("OK") { _: DialogInterface, _: Int -> }
        adb.show()
    }

}
