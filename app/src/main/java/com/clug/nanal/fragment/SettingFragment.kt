package com.clug.nanal.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.activity.Survey4_1Activity
import kotlinx.android.synthetic.main.fragment_setting.*

class SettingFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tv_setting_add_personal_data.setOnClickListener{
            val intent = Intent(activity!!, Survey4_1Activity::class.java)
            startActivity(intent)
        }

    }
}