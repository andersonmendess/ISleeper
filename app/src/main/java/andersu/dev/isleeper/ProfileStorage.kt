package andersu.dev.isleeper

import android.app.Activity
import android.content.Context

class ProfileStorage (activity: Activity) {
    val sharedPref = activity?.getSharedPreferences("PROFILE", Context.MODE_PRIVATE)

    fun save(index: Int) {
        with(sharedPref.edit()) {
            putInt("index", index);
            commit();
        }
    }

    fun read(): Int? {
        return sharedPref.getInt("index", 0);
    }

}