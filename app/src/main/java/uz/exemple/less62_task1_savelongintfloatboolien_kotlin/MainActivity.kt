package uz.exemple.less62_task1_savelongintfloatboolien_kotlin

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {
        val et_ID = findViewById<EditText>(R.id.et_ID)
        val et_Age = findViewById<EditText>(R.id.et_age)
        val et_Weight = findViewById<EditText>(R.id.et_weight)
        val et_isUzbek = findViewById<CheckBox>(R.id.et_isUzbek)
        val b_save = findViewById<Button>(R.id.b_save)
        val b_load = findViewById<Button>(R.id.b_load)
        b_save.setOnClickListener { v ->
            val passport = et_ID.text.toString().trim { it <= ' ' }.toLong()
            val age = et_Age.text.toString().trim { it <= ' ' }.toInt()
            val weight = et_Weight.text.toString().trim { it <= ' ' }.toFloat()
            val isUzbek = et_isUzbek.isChecked

            PrefsManager.getInstance(v.context)!!.saveAll("passport", passport)
            PrefsManager.getInstance(v.context)!!.saveAll("age", age)
            PrefsManager.getInstance(v.context)!!.saveAll("weight", weight)
            PrefsManager.getInstance(v.context)!!.saveAll("isUzbek", isUzbek)

        }
        b_load.setOnClickListener { v ->
            val passport: Serializable =
                PrefsManager.getInstance(v.context)!!.loadData("passport")
            Log.d("###", "Passport is - $passport")
            val age: Serializable = PrefsManager.getInstance(v.context)!!.loadData("age")
            Log.d("###", "Age is - $age")
            val weight: Serializable =
                PrefsManager.getInstance(v.context)!!.loadData("weight")
            Log.d("###", "Weight is - $weight")
            val isUzbek: Serializable =
                PrefsManager.getInstance(v.context)!!.loadData("isUzbek")
            Log.d("###", "Is Uzbek is - $isUzbek")
        }
    }
}