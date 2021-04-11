package com.example.todoapp.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.todoapp.tasks.Task
import java.time.Instant
import java.util.*
import kotlin.concurrent.timerTask


class TaskNotificationService : Service() {

    private lateinit var notificationHandler: NotificationHandler
    private lateinit var taskList: ArrayList<Task>
    private lateinit var timer: Timer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        notificationHandler = NotificationHandler(applicationContext)
        taskList = intent!!.getSerializableExtra("dataSet") as ArrayList<Task>
        timer = Timer()
        timerTask {

        }
        timer.scheduleAtFixedRate(timerTask {
//            TO SHOW AS TEST
//            notificationHandler.showNotification(taskList[0])
            taskList.forEach { task ->
                val secDiff = (task.date.timeInMillis - Calendar.getInstance().timeInMillis)/1000
                if (secDiff in 0..60){
                    notificationHandler.showNotification(task)
                }
            }
        }, Date.from(Instant.now()), 30*1000)

        return START_STICKY
    }

    override fun onDestroy() {
        timer.cancel()
        taskList = emptyList<Task>() as ArrayList<Task>
        super.onDestroy()
    }

}