package com.Audisankara.asit.Feautres

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.Audisankara.asit.MainActivity


class AlarmReceiver : BroadcastReceiver(){
    private val CHANNEL_ID = "this.is.my.channelId" //you can add any id you want

    override fun onReceive(context: Context, intent: Intent?) {
        val notificationIntent =
            Intent(context, MainActivity::class.java) //on tap this activity will open
        val stackBuilder: TaskStackBuilder = TaskStackBuilder.create(context)
        stackBuilder.addParentStack(MainActivity::class.java)
        stackBuilder.addNextIntent(notificationIntent)
        val pendingIntent: PendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT
        ) //getting the pendingIntent
        val builder: Notification.Builder =
            Notification.Builder(context) //building the notification
        val notification: Notification = builder.setContentTitle("Hello there, ☺️")
            .setContentText("Welcoming you to Asit")
            .setTicker("New Message Alert!")
            .setSmallIcon(com.owl93.dpb.R.mipmap.ic_launcher_round)
            .setContentIntent(pendingIntent).build()
        builder.setChannelId(CHANNEL_ID)
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //below creating notification channel, because of androids latest update, O is Oreo
        val channel = NotificationChannel(
            CHANNEL_ID,
            "NotificationDemo",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(0, notification)
    }
}