package com.clug.nanal.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.clug.nanal.R
import kotlinx.android.synthetic.main.activity_start.*
import android.support.v4.os.HandlerCompat.postDelayed
import com.clug.nanal.db.SharedPreferenceController


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        Handler().postDelayed({
            if(SharedPreferenceController.getUserName(this)==""){
                val intent = Intent(this, Survey1Activity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)// 0.5초 정도 딜레이를 준 후 시작
    }
}

