package com.example.todoapp.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.R
import com.example.todoapp.tasks.Task
import com.example.todoapp.tasks.Type
import java.util.*
import com.example.todoapp.Config

class CreateTaskActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
        setContentView(R.layout.add_task_activity)
    }

    fun createTaskAndExit(v: View){

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, 2021)
        calendar.set(Calendar.MONTH, 3)
        calendar.set(Calendar.DAY_OF_MONTH, 6)

        val task = Task("Task1",
                Type.HOME,
                0,
                calendar,
                ""
        )

        val intent = Intent()
        intent.putExtra(Config.TASK_BUNDLE_ID, task)
        setResult(RESULT_OK, intent)

        finish()
        overridePendingTransition(R.anim.fadein, R.anim.fadeout)
    }
}