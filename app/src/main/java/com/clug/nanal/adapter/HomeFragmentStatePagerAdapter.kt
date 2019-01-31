package com.clug.nanal.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.clug.nanal.db.SharedPreferenceController
import com.clug.nanal.fragment.*

class HomeFragmentStatePagerAdapter(ctx : Context, fm: FragmentManager, val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {

    val context = ctx

    override fun getItem(position: Int): Fragment? {

        /*
          if(SharedPreferenceController.getHome1(context)=="유"){
           return HomeWeatherFragment()
       }
         */


        when (position) {
            0 -> return HomeWeatherFragment()
            1 -> return HomeDustFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount

    /*
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
     */

}