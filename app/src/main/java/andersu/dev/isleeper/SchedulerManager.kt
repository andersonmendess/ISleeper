package andersu.dev.isleeper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

class SchedulerManager(context: Context) {
    private val alarm : AlarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager;
    private val broadCastIntent = Intent(context, AlarmBroadcast::class.java)
    private val intent: PendingIntent = PendingIntent.getBroadcast(context, 0,broadCastIntent, 0);

    fun setAlarm(minutes: Int) {
        val calendar: Calendar = Calendar.getInstance()

        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH),
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE) + minutes,
        )

        alarm.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, intent);
    }

    fun cancel() {
        alarm.cancel(intent);
    }
}