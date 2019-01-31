package com.clug.nanal.activity

import android.content.Intent
import android.content.SharedPreferences
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

class Survey3Activity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey3)

        btn_survey3_ok.setOnClickListener{

            if(cloth.isChecked){
                SharedPreferenceController.setHome1True(this)
                SharedPreferenceController.setCount(this, SharedPreferenceController.getCount(this)+1)
            }
            if(dust.isChecked){
                SharedPreferenceController.setHome2True(this)
                SharedPreferenceController.setCount(this, SharedPreferenceController.getCount(this)+1)
            }

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}