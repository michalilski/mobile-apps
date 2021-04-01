package com.example.todoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import com.example.todoapp.tasks.Type
import java.util.*
import com.example.todoapp.Config
import com.example.todoapp.Utils
import com.example.todoapp.ui.RecyclerAdapter

class CreateTaskActivity : AppCompatActivity() {

    private var currentType = Type.HOME
    private var currentPriority = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.add_task_activity)
        setupForm()
    }

    override fun onBackPressed(){
        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    private fun setupForm(){
        val typeList = findViewById<LinearLayout>(R.id.typeList)
        Utils.typeMap.forEach{
            val textView = TextView(this)
            textView.text = it.value
            textView.setOnClickListener {_ ->
                Toast.makeText(this, textView.text, Toast.LENGTH_SHORT).show()
                currentType = it.key
            }
            typeList.addView(textView)
        }

        val priorityList = findViewById<LinearLayout>(R.id.priorityList)
        Utils.priorityMap.forEach{
            val textView = TextView(this)
            textView.text = it.value
            textView.setOnClickListener {_ ->
                Toast.makeText(this, textView.text, Toast.LENGTH_SHORT).show()
                currentPriority = it.key
            }
            priorityList.addView(textView)
        }

        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)
    }

    fun createTaskAndExit(v: View){

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2021)
        calendar.set(Calendar.MONTH, 3)
        calendar.set(Calendar.DAY_OF_MONTH, 6)

        val task = Task(getTaskTitle(),
                currentType,
                currentPriority,
                calendar,
                getTaskNote()
        )

        val intent = Intent()
        intent.putExtra(Config.TASK_BUNDLE_ID, task)
        setResult(RESULT_OK, intent)

        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }

    private fun getTaskTitle() : String {
        val title = findViewById<TextView>(R.id.titleInput).text.toString()
        if (title == ""){
            return "Untitled"
        }
        return title
    }

    private fun getTaskNote() : String {
        val note = findViewById<TextView>(R.id.taskNoteInput).text.toString()
        if (note == ""){
            return "There are no notes for this task."
        }
        return note
    }
}