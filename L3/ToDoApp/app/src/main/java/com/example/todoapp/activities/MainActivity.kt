package com.example.todoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import com.example.todoapp.ui.RecyclerAdapter
import com.example.todoapp.Config


class MainActivity : AppCompatActivity() {

/*
TODO:
- icons
- task date
- improve type/priority list interface
*/

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecycler()
    }

    private fun setupRecycler() {
        recyclerView = findViewById(R.id.recyclerAdapter)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerAdapter(arrayListOf())
        recyclerView.adapter = recyclerAdapter
        recyclerAdapter.setActivity(this)
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
}