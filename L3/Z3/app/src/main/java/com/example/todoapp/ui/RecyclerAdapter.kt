package com.example.todoapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Paint
import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Config
import com.example.todoapp.R
import com.example.todoapp.Utils
import com.example.todoapp.db.TaskDao
import com.example.todoapp.tasks.Task
import java.text.SimpleDateFormat

class RecyclerAdapter(val dataSet: ArrayList<Task>) :
        RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    @SuppressLint("SimpleDateFormat")
    private val dt = SimpleDateFormat(Config.DATE_FORMAT)
    private lateinit var activity: Activity
    private lateinit var taskDao: TaskDao


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskTitleTextView: TextView = view.findViewById(R.id.taskTitleTextView)
        val taskDateTextView: TextView = view.findViewById(R.id.taskDateTextView)
        val taskPriorityImageView: ImageView = view.findViewById(R.id.taskPriorityImageView)
        val taskIconImageView: ImageView = view.findViewById(R.id.taskIconImageView)

        init {
            view.setOnClickListener {
                infoAlert(adapterPosition)
            }
            view.setOnLongClickListener {
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
        Utils.priorityMap[dataSet[position].priority]?.let { viewHolder.taskPriorityImageView.setImageResource(it) }
        Utils.typeMap[dataSet[position].type]?.let(viewHolder.taskIconImageView::setImageResource)
        if (dataSet[position].passed)
            viewHolder.taskTitleTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        else if (dataSet[position].today)
            viewHolder.taskTitleTextView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
        else
            viewHolder.taskTitleTextView.paintFlags = 0
    }

    override fun getItemCount() = dataSet.size

    fun setActivity(activity: Activity, taskDao: TaskDao) {
        this.activity = activity
        this.taskDao = taskDao
    }

    fun addTask(task: Task) {
        dataSet.add(task)
        notifyItemInserted(dataSet.size - 1)
    }

    fun updateRecords() {
        for (position in 0 until dataSet.size) {
            if (taskPassed(position))
                dataSet[position].passed = true
            else if (isToday(position))
                dataSet[position].today = true
        }
        notifyDataSetChanged()
    }

    private fun deleteTask(taskId: Int) {
        taskDao.delete(dataSet[taskId].uid)
        dataSet.removeAt(taskId)
        notifyItemRemoved(taskId)
    }

    private fun taskPassed(position: Int) : Boolean{
        val yesterday = Calendar.getInstance()
        yesterday.set(Calendar.HOUR_OF_DAY, 0)
        if (dataSet[position].date.time <= yesterday.time)
            return true
        return false
    }

    private fun isToday(position: Int) : Boolean{
        val today = Calendar.getInstance()
        today.set(Calendar.HOUR_OF_DAY, 0)
        val todayTime = today.time
        today.add(Calendar.DATE, 1)
        val tomorrowTime = today.time

        if (dataSet[position].date.time in todayTime..tomorrowTime)
            return true
        return false
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
        Utils.typeMap[dataSet[adapterPosition].type]?.let { adb.setIcon(it) }
        adb.setMessage("Note:\n" + dataSet[adapterPosition].note)
        adb.setPositiveButton("OK") { _: DialogInterface, _: Int -> }
        adb.show()
    }

}
