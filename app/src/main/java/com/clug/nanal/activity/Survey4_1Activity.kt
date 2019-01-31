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
import org.jetbrains.anko.toast

class Survey4_1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey4_1)

        if(SharedPreferenceController.getUserLocationLarge(this).equals("")&&SharedPreferenceController.getNowTempo(this)==0.0F){
            toast("위치 정보가 없습니다. 먼저 위치를 설정해주세요")
            finish()
        }else{
            btn_survey4_1_ok.setOnClickListener {
                val intent = Intent(this, Survey4_2Activity::class.java)
                intent.putExtra("tempo", getUserTemp(SharedPreferenceController.getNowTempo(this)))
                startActivity(intent)
                finish()
            }
        }
    }

    internal fun getUserTemp(temp: Float): Float {
        var userTemp = temp

        if (cardigan.isChecked) userTemp += 2
        if (zipup.isChecked) userTemp += 3
        if (jacket.isChecked) userTemp += 4
        if (leather.isChecked) userTemp += 5
        if (coat.isChecked) userTemp += 7
        if (padding.isChecked) userTemp += 10

        return userTemp
    }
}