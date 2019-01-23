package com.clug.nanal.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.clug.nanal.R
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_change_person_data.*

class ChangePersonDataActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_person_data)

        setViewClickListener()
    }

    fun setViewClickListener() {
        img_change_person_data_back.setOnClickListener {
            finish()
        }

        tv_change_person_data_clear.setOnClickListener {
            SharedPreferenceController.clearUserSharedPreferences(this)
            val intent: Intent = Intent(this, SplashActivity::class.java)
            startActivity(intent)
        }
    }
}
