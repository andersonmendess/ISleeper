package andersu.dev.isleeper

import android.app.admin.DevicePolicyManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class AlarmBroadcast : BroadcastReceiver() {
    private lateinit var devicePolicyManager: DevicePolicyManager;

    override fun onReceive(p0: Context?, p1: Intent?) {
        val mAudioManager = p0?.getSystemService(Context.AUDIO_SERVICE) as AudioManager
        val notificationManager = NotificationManager();


//        val intent = Intent("sleep_tile")
//        intent.putExtra("state", "off");
//
//        LocalBroadcastManager.getInstance(p0).sendBroadcast(intent);

        devicePolicyManager = p0?.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager;

        if(mAudioManager.isMusicActive) {
            val event = KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_MEDIA_PAUSE)
            mAudioManager.dispatchMediaKeyEvent(event)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            // remove app notifications
            notificationManager.cancelAll(p0);

            // Lock the phone screen;
            devicePolicyManager.lockNow();
        }, 1000)

    }
}