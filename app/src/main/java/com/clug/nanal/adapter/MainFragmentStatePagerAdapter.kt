package com.clug.nanal.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.clug.nanal.fragment.AddviewFragment
import com.clug.nanal.fragment.HomeFragment
import com.clug.nanal.fragment.SettingFragment

class MainFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCount: Int):FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment? {
        when(position){
            0 -> return HomeFragment()
            1 -> return AddviewFragment()
            2 -> return SettingFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCount
}