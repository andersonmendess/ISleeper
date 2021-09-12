package andersu.dev.isleeper

import android.app.NotificationChannel
import android.os.Build
import android.app.NotificationManager
import android.content.ClipDescription
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationManager {
    fun createChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "NOTIFICATIONS"
            val descriptionText = "GET NOTIFICATIONS ABOUT NEXT SLEEP TIME"
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel("1", name, importance).apply {
                description = descriptionText
            }
            channel.setSound(null, null);
            // Register the channel with the system
            val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

    fun notify(title: String, description: String, context: Context) {
        var builder = NotificationCompat.Builder(context, "1")
            .setSmallIcon(R.drawable.ic_baseline_king_bed_24)
            .setContentTitle(title)
            .setContentText(description)
            .setPriority(NotificationCompat.PRIORITY_LOW)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(1, builder.build())
        }
    }

    fun cancelAll(context: Context) {
        val notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.cancelAll();
    }
}