package hr.ferit.brunozoric.taskie.persistence

import android.preference.PreferenceManager
import androidx.room.PrimaryKey
import hr.ferit.brunozoric.taskie.Taskie

object TaskPreferences {
    const val KEY_PREFS_NAME = "KEY_PREFS_NAME"
    private fun sharedPreferences() =PreferenceManager.getDefaultSharedPreferences(Taskie.getAppContext())

    fun save(key: String, value: String){
        val editor = sharedPreferences().edit()
        editor.putString(key, value).apply()
    }

    fun getString(key: String, value: String): String? = sharedPreferences().getString(key, value)
}