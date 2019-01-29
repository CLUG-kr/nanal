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
import org.jetbrains.anko.support.v4.toast
import java.util.*

class HomeWeatherFragment : Fragment() {

    lateinit var name: String
    lateinit var sex: String
    var tempo: Int = 0
    lateinit var location: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homeweather, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        getSharedPreferenceController()

        setCloset()
    }

    fun getSharedPreferenceController() {
        name = SharedPreferenceController.getUserName(activity!!)
        sex = SharedPreferenceController.getUserSex(activity!!)
        tempo = SharedPreferenceController.getUserTempo(activity!!)
        location = SharedPreferenceController.getUserLocation(activity!!)
    }

    fun setCloset() {
        //Log.e(SharedPreferenceController.getUserSex(activity!!), "오노")
        //디비에서 꺼내서 이미지 설정하기
    }
}