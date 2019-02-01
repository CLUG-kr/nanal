package com.clug.nanal.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import com.clug.nanal.R
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_survey2.*
import kotlinx.android.synthetic.main.activity_survey3.*
import kotlinx.android.synthetic.main.activity_survey4_1.*
import kotlinx.android.synthetic.main.activity_survey4_2.*
import kotlinx.android.synthetic.main.activity_survey4_3.*
import kotlinx.android.synthetic.main.activity_survey5.*
import org.jetbrains.anko.toast
import java.util.*

class Survey5Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey5)

        val preTempo = intent.extras!!.getFloat("tempo")


        btn_survey5_ok.setOnClickListener {
            if (soCold.isChecked || cold.isChecked || fine.isChecked || hot.isChecked || soHot.isChecked) {
                SharedPreferenceController.setUserTempo(this,
                        getRefTemp(preTempo, SharedPreferenceController.getUserTempo(this), SharedPreferenceController.getUserType(this)))
                toast("" + SharedPreferenceController.getUserTempo(this))
                finish()
            }else{
                toast("값을 선택해주세요")
            }
        }
    }

    internal fun getRefTemp(refTemp: Float, userTemp: Float, type: Int): Float {

        var userTemp = userTemp

        when (type) {
            1 -> {  //추위 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 4
                else if (cold.isChecked)
                    userTemp += 2
                else if (soHot.isChecked)
                    userTemp -= 2
                else if (hot.isChecked) userTemp -= 1
            }
            2 -> {  //더위 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 2
                else if (cold.isChecked)
                    userTemp += 1
                else if (soHot.isChecked)
                    userTemp -= 4
                else if (hot.isChecked) userTemp -= 2

            }
            3 -> {  //둘 다 잘 타는 사람
                if (soCold.isChecked)
                    userTemp += 2
                else if (cold.isChecked)
                    userTemp += 1
                else if (soHot.isChecked)
                    userTemp -= 2
                else if (hot.isChecked) userTemp -= 1
            }
            4 -> {  //둘 다 잘 안타는 사람
                if (soCold.isChecked)
                    userTemp += 4
                else if (cold.isChecked)
                    userTemp += 2
                else if (soHot.isChecked)
                    userTemp -= 4
                else if (hot.isChecked) userTemp -= 2
            }
        }
        if(userTemp<18) userTemp=18.0F
        if(userTemp>26) userTemp=26.0F
        return (refTemp+userTemp)/2
    }

    fun onlyOneChecked(v: View) {
        val soCold = findViewById<View>(R.id.soCold) as RadioButton
        val cold = findViewById<View>(R.id.cold) as RadioButton
        val fine = findViewById<View>(R.id.fine) as RadioButton
        val hot = findViewById<View>(R.id.hot) as RadioButton
        val soHot = findViewById<View>(R.id.soHot) as RadioButton

        when (v.id) {
            R.id.soCold -> {
                cold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.cold -> {
                soCold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.fine -> {
                soCold.isChecked = false
                cold.isChecked = false
                hot.isChecked = false
                soHot.isChecked = false
            }
            R.id.hot -> {
                soCold.isChecked = false
                cold.isChecked = false
                fine.isChecked = false
                soHot.isChecked = false
            }
            R.id.soHot -> {
                soCold.isChecked = false
                cold.isChecked = false
                fine.isChecked = false
                hot.isChecked = false
            }
        }
    }


}