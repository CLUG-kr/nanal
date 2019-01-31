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

class Survey4_1Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_1)

        btn_survey4_1_ok.setOnClickListener{
            getUserTemp(26)

            val intent = Intent(this, Survey4_2Activity::class.java)
            startActivity(intent)
            finish()
        }
    }

    internal fun getUserTemp(temp: Int): Int {
        var userTemp = SharedPreferenceController.getUserTempo(this)
        val cardigan = findViewById<View>(R.id.cardigan) as CheckBox
        val zipup = findViewById<View>(R.id.zipup) as CheckBox
        val jacket = findViewById<View>(R.id.jacket) as CheckBox
        val leather = findViewById<View>(R.id.leather) as CheckBox
        val coat = findViewById<View>(R.id.coat) as CheckBox
        val padding = findViewById<View>(R.id.padding) as CheckBox
        userTemp = temp

        if (cardigan.isChecked) userTemp += 2
        if (zipup.isChecked) userTemp += 3
        if (jacket.isChecked) userTemp += 4
        if (leather.isChecked) userTemp += 5
        if (coat.isChecked) userTemp += 7
        if (padding.isChecked) userTemp += 10

        return userTemp
    }
}