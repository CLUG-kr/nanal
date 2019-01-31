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

class Survey4_3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_3)

        val preTempo = intent.extras!!.getFloat("tempo")

        btn_survey4_3_ok.setOnClickListener {
            val intent = Intent(this, Survey5Activity::class.java)
            intent.putExtra("tempo", getUserTemp(preTempo))
            startActivity(intent)
            finish()
        }
    }

    internal fun getUserTemp(temp: Float): Float {
        var userTemp = temp

        if (pants.isChecked) userTemp += 1
        if (leggings.isChecked) userTemp += 2
        if (thick_pants.isChecked) userTemp += 3
        if (stocking.isChecked) userTemp += 1

        return userTemp
    }
}