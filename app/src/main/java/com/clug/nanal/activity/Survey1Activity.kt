package com.clug.nanal.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import com.clug.nanal.R
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_survey1.*
import org.jetbrains.anko.toast

class Survey1Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey1)

        btn_survey1_ok.setOnClickListener {

            if (usernameInput.text.toString() != "" && (Male.isChecked || Female.isChecked)) {
                SharedPreferenceController.setUserName(this, usernameInput.text.toString())
                if(Male.isChecked){
                    SharedPreferenceController.setUserSex(this, "남")
                }else{
                    SharedPreferenceController.setUserSex(this, "여")
                }

                val intent = Intent(this, Survey2Activity::class.java)
                intent.putExtra("name", usernameInput.text.toString())
                startActivity(intent)

                finish()
            } else {
                toast("정보가 비워져 있습니다. 입력해주세요.")
            }
        }
    }

    fun onlyOneGender(v: View) {
        val male = findViewById<View>(R.id.Male) as CheckBox
        val female = findViewById<View>(R.id.Female) as CheckBox
        when (v.id) {
            R.id.Male -> female.isChecked = false
            R.id.Female -> male.isChecked = false
        }
    }
}