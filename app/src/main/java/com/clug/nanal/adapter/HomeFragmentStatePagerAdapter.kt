package com.clug.nanal.adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import com.clug.nanal.db.SharedPreferenceController
import com.clug.nanal.fragment.*

class HomeFragmentStatePagerAdapter(ctx: Context, fm: FragmentManager, val fragmentCount: Int) : FragmentStatePagerAdapter(fm) {

    val context = ctx
    val fragments: ArrayList<Fragment> = ArrayList()

    override fun getItem(position: Int): Fragment? {

        setFragments()

        for (i in 0..SharedPreferenceController.getCount(context)) {
            if (position == i) {
                return fragments[i]
            }
        }
        return null
    }

    override fun getCount(): Int = fragmentCount

    fun setFragments() {
        if (SharedPreferenceController.getHome1(context) == "유") {
            fragments.add(HomeWeatherFragment())
        }
        if (SharedPreferenceController.getHome2(context) == "유") {
            fragments.add(HomeDustFragment())
        }
        if (SharedPreferenceController.getHome3(context) == "유") {
            fragments.add(HomeWeekFragment())
        }
        if (SharedPreferenceController.getHome4(context) == "유") {
            fragments.add(HomeMountainFragment())
        }
        if (SharedPreferenceController.getHome5(context) == "유") {
            fragments.add(HomeFishingFragment())
        }
    }
}