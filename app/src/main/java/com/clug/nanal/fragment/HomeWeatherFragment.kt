package com.clug.nanal.fragment

import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.adapter.GpsInfo
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_location.*
import org.jetbrains.anko.db.INTEGER
import org.jetbrains.anko.support.v4.toast
import java.util.*

class HomeWeatherFragment : Fragment() {

    lateinit var name: String
    lateinit var sex: String
    var tempo: Int = 0
    lateinit var location_Big: String
    lateinit var location_Small: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homeweather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSharedPreferenceController()

        setView()

        setCloset()
    }

    fun getSharedPreferenceController() {
        name = SharedPreferenceController.getUserName(activity!!)
        sex = SharedPreferenceController.getUserSex(activity!!)
        tempo = SharedPreferenceController.getUserTempo(activity!!)
        location_Big = SharedPreferenceController.getUserLocationBig(activity!!)
        location_Small = SharedPreferenceController.getUserLocationSmall(activity!!)
    }

    fun setView() {
        //민지 위치 정보 가지고 뷰 설정하기
    }

    fun setCloset() {
        //나래 사용자 정보가지고 옷 설정하기
    }
}