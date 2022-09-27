package uz.exemple.less62_task1_savelongintfloatboolien_kotlin

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var prefs: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
        prefs = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        editor = prefs.edit()

        val et_ID = findViewById<EditText>(R.id.et_ID)
        val et_Age = findViewById<EditText>(R.id.et_age)
        val et_Weight = findViewById<EditText>(R.id.et_weight)
        val et_isUzbek = findViewById<CheckBox>(R.id.et_isUzbek)
        val b_save = findViewById<Button>(R.id.b_save)
        val b_load = findViewById<Button>(R.id.b_load)
        val res = findViewById<TextView>(R.id.tv_result)
        b_save.setOnClickListener { v ->
            val passport = et_ID.text.toString().trim { it <= ' ' }.toLong()
            val age = et_Age.text.toString().trim { it <= ' ' }.toInt()
            val weight = et_Weight.text.toString().trim { it <= ' ' }.toFloat()
            val isUzbek = et_isUzbek.isChecked
            savePassport("passport", passport)
            saveAge("age", age)
            saveWeight("weight", weight)
            saveNationality("isUzbek", isUzbek)
            et_ID.setText("")
            et_Age.setText("")
            et_Weight.setText("")
            et_isUzbek.isChecked = false


        }
        b_load.setOnClickListener { v ->
            val res_passport = loadPassport()!!
            val res_age = loadAge()
            val res_weight = loadWeight()
            val res_isUzbek = loadNationality()

            res.setVisibility(View.VISIBLE)
            res.setText("PassportId - $res_passport,\n\nAge - $res_age,\n\nWeight - $res_weight,\n\nIz uzbek - $res_isUzbek")

        }
    }

    fun savePassport(key: String?, passport: Long?) {
        editor.putLong("passport", passport!!)
        editor.apply()
    }

    fun loadPassport(): Long? {
        val prefs = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        return prefs.getLong("passport", 0)
    }

    fun saveAge(key: String?, age: Int) {
        editor.putInt("age", age)
        editor.apply()
    }

    fun loadAge(): Int {
        val prefs = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        return prefs.getInt("age", 0)
    }

    fun saveWeight(key: String?, weight: Float) {
        editor.putFloat("weight", weight)
        editor.apply()
    }

    fun loadWeight(): Float {
        val prefs = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        return prefs.getFloat("weight", 0f)
    }

    fun saveNationality(key: String?, isUzbek: Boolean) {
        editor.putBoolean("isUzbek", isUzbek)
        editor.apply()
    }

    fun loadNationality(): Boolean {
        val prefs = applicationContext.getSharedPreferences("MyPref", MODE_PRIVATE)
        return prefs.getBoolean("isUzbek", false)
    }
}