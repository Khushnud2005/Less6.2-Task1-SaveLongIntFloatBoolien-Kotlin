package uz.exemple.less62_task1_savelongintfloatboolien_kotlin

import android.content.Context
import android.content.SharedPreferences
import java.io.Serializable

class PrefsManager(context: Context) {
    private var sharedPreference:SharedPreferences?

    companion object{
        private var prefsManager:PrefsManager? = null
        fun getInstance(context: Context):PrefsManager?{
            if (prefsManager==null){
                prefsManager = PrefsManager(context)
            }
            return prefsManager
        }
    }
    init {
        sharedPreference = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    }

    fun saveData(key:String?,value:String?){
        val prefsEditor = sharedPreference!!.edit()
        prefsEditor.putString(key,value)
        prefsEditor.commit()
    }
    fun <T> saveAll(key: String, value: T) {
        val prefsEditor: SharedPreferences.Editor = sharedPreference!!.edit()
        if (key == "passport") {
            prefsEditor.putLong(key, (value as Long))
        } else if (key == "age") {
            prefsEditor.putInt(key, (value as Int))
        } else if (key == "weight") {
            prefsEditor.putFloat(key, (value as Float))
        } else if (key == "isUzbek") {
            prefsEditor.putBoolean(key, value as Boolean)
        } else {
            prefsEditor.putString(key, value.toString())
        }
        prefsEditor.commit()
    }
    fun loadData(key:String?): Serializable {
        if (sharedPreference != null) {
            if (key == "passport") {
                return sharedPreference!!.getLong(key, 0)
            } else if (key == "age") {
                return sharedPreference!!.getInt(key, 0)
            } else if (key == "weight") {
                return sharedPreference!!.getFloat(key, 0f)
            } else if (key == "isUzbek") {
                return sharedPreference!!.getBoolean(key, false)
            }
        }

        return ""
    }
    fun clearAll() {
        val editor = sharedPreference!!.edit()
        editor.clear()
        editor.apply()
    }
}