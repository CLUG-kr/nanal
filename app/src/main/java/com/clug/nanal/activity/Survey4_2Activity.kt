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

class Survey4_2Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_2)

        btn_survey4_2_ok.setOnClickListener{
            val intent = Intent(this, Survey4_3Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    internal fun getUserTemp(userTemp: Int, temp: Int): Int {
        var userTemp = userTemp
        val tshirt = findViewById<View>(R.id.tshirt) as CheckBox
        val shirt = findViewById<View>(R.id.shirt) as CheckBox
        val mtm = findViewById<View>(R.id.mtm) as CheckBox
        val hood = findViewById<View>(R.id.hood) as CheckBox
        val knit = findViewById<View>(R.id.knit) as CheckBox
        val polar = findViewById<View>(R.id.polar_t) as CheckBox
        userTemp = temp

        if (tshirt.isChecked) userTemp += 1
        if (shirt.isChecked) userTemp += 1
        if (mtm.isChecked) userTemp += 3
        if (hood.isChecked) userTemp += 3
        if (knit.isChecked) userTemp += 5
        if (polar.isChecked) userTemp = 7

        return userTemp
    }
}