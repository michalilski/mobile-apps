package com.example.todoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.Config
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import com.example.todoapp.tasks.Type
import com.example.todoapp.ui.RecyclerAdapter
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter
    private var timeSortWay = 1
    private var prioritySortWay = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler(arrayListOf())
        setupActivity()
    }

    override fun onResume() {
        super.onResume()
        recyclerAdapter.updateRecords()
    }

    override fun onStart() {
        super.onStart()
        recyclerAdapter.updateRecords()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("tasksDataSet", recyclerAdapter.dataSet as Serializable)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupRecycler(savedInstanceState.getSerializable("tasksDataSet") as ArrayList<Task>)
    }

    private fun setupRecycler(data: ArrayList<Task>) {
        recyclerView = findViewById(R.id.recyclerAdapter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(data)
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.setActivity(this)
    }

    private fun setupActivity() {
        val sortTimeButton = findViewById<ImageButton>(R.id.sortDateButton)
        sortTimeButton.setOnClickListener {
            sortTime()
            timeSortWay *= -1
        }

        val sortTypeButton = findViewById<ImageButton>(R.id.sortTypeButton)
        sortTypeButton.setOnClickListener {
            sortType()
        }

        val sortPriorityButton = findViewById<ImageButton>(R.id.sortPriorityButton)
        sortPriorityButton.setOnClickListener {
            sortPriority()
            prioritySortWay *= -1
        }
    }

    fun addTask(v: View) {
        val intent = Intent(this, CreateTaskActivity::class.java)
        startActivityForResult(intent, Config.ADD_TASK_ACTIVITY_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            Config.ADD_TASK_ACTIVITY_CODE -> {
                if (resultCode == RESULT_OK) {
                    recyclerAdapter.addTask(data?.getSerializableExtra(Config.TASK_BUNDLE_ID) as Task)
                }
            }
        }
    }

    private fun sortTime() {
        recyclerAdapter.dataSet.sortWith { left, right ->
            when {
                left.date < right.date -> -1 * timeSortWay
                left.date > right.date -> timeSortWay
                else -> 0
            }
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun sortType() {
        recyclerAdapter.dataSet.sortWith { left, right ->
            when {
                left.type == right.type -> 0
                left.type == Type.HOME && right.type == Type.WORK -> -1
                left.type == Type.WORK && right.type == Type.GYM -> -1
                left.type == Type.HOME && right.type == Type.GYM -> -1
                else -> 1
            }
        }
        recyclerAdapter.notifyDataSetChanged()
    }

    private fun sortPriority() {
        recyclerAdapter.dataSet.sortWith { left, right ->
            when {
                left.priority < right.priority -> prioritySortWay
                left.priority > right.priority -> -1 * prioritySortWay
                else -> 0
            }
        }
        recyclerAdapter.notifyDataSetChanged()
    }
}