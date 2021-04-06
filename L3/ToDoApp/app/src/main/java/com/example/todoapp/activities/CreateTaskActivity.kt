package com.example.todoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import com.example.todoapp.tasks.Type
import java.util.*
import com.example.todoapp.Config

/*
    TODO
        ikonka priorytetu
        gwiazdki
 */

class CreateTaskActivity : AppCompatActivity() {

    private var currentType = Type.HOME
    private var currentPriority = 2
    private var calendar : Calendar = Calendar.getInstance()
    private lateinit var timePicker : TimePicker

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

        val taskTypeRadioButtonGroup = findViewById<RadioGroup>(R.id.taskTypeRadioGroup)
        taskTypeRadioButtonGroup.check(R.id.homeTaskTypeRadioButton)
        taskTypeRadioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.homeTaskTypeRadioButton ->
                    currentType = Type.HOME
                R.id.gymTaskTypeRadioButton ->
                    currentType = Type.GYM
                R.id.workTaskTypeRadioButton ->
                    currentType = Type.WORK
            }
        }

        val taskPriorityRadioButtonGroup = findViewById<RadioGroup>(R.id.taskPriorityRadioGroup)
        taskPriorityRadioButtonGroup.check(R.id.highPriorityRadioButton)
        taskPriorityRadioButtonGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.highPriorityRadioButton ->
                    currentPriority = 2
                R.id.mediumPriorityRadioButton ->
                    currentPriority = 1
                R.id.lowPriorityRadioButton ->
                    currentPriority = 0
            }
        }


        timePicker = findViewById(R.id.timePicker)
        timePicker.setIs24HourView(true)

        val calendarView = findViewById<CalendarView>(R.id.calendarView)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            calendarView.date = calendar.timeInMillis
        }
    }

    fun createTaskAndExit(v: View){
        calendar.set(Calendar.HOUR_OF_DAY, timePicker.hour)
        calendar.set(Calendar.MINUTE, timePicker.minute)

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