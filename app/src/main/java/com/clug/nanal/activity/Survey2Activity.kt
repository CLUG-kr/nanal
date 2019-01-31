package com.clug.nanal.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.clug.nanal.R
import kotlinx.android.synthetic.main.activity_survey2.*

class Survey2Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey2)

        val i = intent
        val name = i.extras!!.getString("name")
        val cold = findViewById<View>(R.id.textView2) as TextView
        val hot = findViewById<View>(R.id.textView4) as TextView

        cold.text = name + "님은 추위를 잘 타시나요?"
        hot.text = name + "님은 더위를 잘 타시나요?"

        btn_survey2_ok.setOnClickListener{
            val intent = Intent(this, Survey3Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    internal fun getType(type: Int): Int {
        var type = type
        val yesCold = findViewById<View>(R.id.YesCold) as CheckBox
        val noCold = findViewById<View>(R.id.NoCold) as CheckBox
        val yesHot = findViewById<View>(R.id.YesHot) as CheckBox
        val noHot = findViewById<View>(R.id.YesHot) as CheckBox

        if (yesCold.isChecked && noHot.isChecked)
            type = 1
        else if (noCold.isChecked && yesHot.isChecked)
            type = 2
        else if (yesCold.isChecked && yesHot.isChecked)
            type = 3
        else
            type = 4

        return type
    }

    fun onlyOneChecked(v: View) {
        val yesCold = findViewById<View>(R.id.YesCold) as CheckBox
        val noCold = findViewById<View>(R.id.NoCold) as CheckBox
        val yesHot = findViewById<View>(R.id.YesHot) as CheckBox
        val noHot = findViewById<View>(R.id.NoHot) as CheckBox

        when (v.id) {
            R.id.YesCold -> noCold.isChecked = false
            R.id.NoCold -> yesCold.isChecked = false
            R.id.YesHot -> noHot.isChecked = false
            R.id.NoHot -> yesHot.isChecked = false
        }
    }
}