package com.clug.nanal.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.adapter.HomeFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment: Fragment(){

    var homeCount : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        vp_home_navi_act_frag_pager.adapter = HomeFragmentStatePagerAdapter(childFragmentManager, homeCount)
        vp_home_navi_act_frag_pager.offscreenPageLimit = 2

        pageIndicatorView.count = 2
        pageIndicatorView.selection = 1

        vp_home_navi_act_frag_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView.selection = position
                var current = vp_home_navi_act_frag_pager.adapter!!.instantiateItem(vp_home_navi_act_frag_pager,position)
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })
    }
}