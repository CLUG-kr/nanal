package com.clug.nanal.fragment

import android.location.Geocoder
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import java.util.*

class HomeWeatherFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homeweather, container, false)

        getLocation()
    }

    fun getLocation(){
        /*
        val gCoder = Geocoder(activity!!, Locale.getDefault())
        val addr = gCoder.getFromLocation(lat, lng, 1)
        val a = addr[0]

        a.adminArea+

        a.getAdminArea()+" "+a.getLocality()+" "+a.getThoroughfare()
         */
    }
}