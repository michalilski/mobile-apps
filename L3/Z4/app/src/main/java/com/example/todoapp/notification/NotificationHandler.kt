package com.example.todoapp.notification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.todoapp.Config
import com.example.todoapp.Utils.Companion.typeMap
import com.example.todoapp.tasks.Task
import java.text.SimpleDateFormat

class NotificationHandler(private val context: Context) {

    @SuppressLint("SimpleDateFormat")
    private val format = SimpleDateFormat(Config.DATE_FORMAT)


    fun createChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel"
            val descriptionText = "Desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel("channel_id", name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(context, NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    fun showNotification(task: Task) {
        val builder = NotificationCompat.Builder(context, "channel_id")
            .setSmallIcon(typeMap[task.type]!!)
            .setContentTitle("Hey! You have incoming task to do!")
            .setContentText("${task.title} on ${format.format(task.date.time)}")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(0, builder.build())
        }
    }
}