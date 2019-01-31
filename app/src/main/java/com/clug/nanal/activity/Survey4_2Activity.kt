package com.clug.nanal.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import com.clug.nanal.R
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_survey2.*
import kotlinx.android.synthetic.main.activity_survey3.*
import kotlinx.android.synthetic.main.activity_survey4_1.*
import kotlinx.android.synthetic.main.activity_survey4_2.*

class Survey4_2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_2)

        val preTempo = intent.extras!!.getFloat("tempo")

        btn_survey4_2_ok.setOnClickListener {
            val intent = Intent(this, Survey4_3Activity::class.java)
            intent.putExtra("tempo", getUserTemp(preTempo))
            startActivity(intent)
            finish()
        }
    }

    internal fun getUserTemp(temp: Float): Float {
        var userTemp = temp

        if (tshirt.isChecked) userTemp += 1
        if (shirt.isChecked) userTemp += 1
        if (mtm.isChecked) userTemp += 3
        if (hood.isChecked) userTemp += 3
        if (knit.isChecked) userTemp += 5
        if (polar_t.isChecked) userTemp += 7

        return userTemp
    }
}