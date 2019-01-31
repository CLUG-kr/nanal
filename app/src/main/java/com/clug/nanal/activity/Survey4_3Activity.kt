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
import kotlinx.android.synthetic.main.activity_survey3.*
import kotlinx.android.synthetic.main.activity_survey4_1.*
import kotlinx.android.synthetic.main.activity_survey4_2.*
import kotlinx.android.synthetic.main.activity_survey4_3.*

class Survey4_3Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_3)

        btn_survey4_3_ok.setOnClickListener{
            val intent = Intent(this, Survey5Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    internal fun getUserTemp(userTemp: Int, temp: Int): Int {
        var userTemp = userTemp
        val pants = findViewById<View>(R.id.pants) as CheckBox
        val thickPants = findViewById<View>(R.id.thick_pants) as CheckBox
        val stocking = findViewById<View>(R.id.stocking) as CheckBox
        val leggings = findViewById<View>(R.id.leggings) as CheckBox
        userTemp = temp

        if (pants.isChecked) userTemp += 1
        if (leggings.isChecked) userTemp += 2
        if (thickPants.isChecked) userTemp += 3
        if (stocking.isChecked) userTemp += 1

        return userTemp
    }
}