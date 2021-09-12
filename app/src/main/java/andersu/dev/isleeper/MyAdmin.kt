package andersu.dev.isleeper

import android.app.admin.DeviceAdminReceiver
import android.content.Context
import android.content.Intent


class MyAdmin : DeviceAdminReceiver() {
    override fun onEnabled(context: Context, intent: Intent) {}

    override fun onDisabled(context: Context, intent: Intent) {}
}