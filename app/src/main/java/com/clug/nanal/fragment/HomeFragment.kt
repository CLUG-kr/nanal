package com.clug.nanal.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.clug.nanal.R
import com.clug.nanal.adapter.HomeFragmentStatePagerAdapter
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.support.v4.toast

class HomeFragment : Fragment() {

    var homeCount: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeCount=SharedPreferenceController.getCount(activity!!)

        Log.e("homecount", ""+homeCount)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        if(SharedPreferenceController.getUserLocationLarge(activity!!).equals("")){
            vp_home_navi_act_frag_pager.visibility = View.GONE
            pageIndicatorView.visibility = View.GONE
            tv_home_no_location.visibility = View.VISIBLE
        }else{
            vp_home_navi_act_frag_pager.visibility = View.VISIBLE
            pageIndicatorView.visibility = View.VISIBLE
            tv_home_no_location.visibility = View.GONE

            vp_home_navi_act_frag_pager.adapter = HomeFragmentStatePagerAdapter(activity!!,childFragmentManager, homeCount)
            vp_home_navi_act_frag_pager.offscreenPageLimit = homeCount

            pageIndicatorView.count = homeCount
            pageIndicatorView.selection = 0

            vp_home_navi_act_frag_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
                }

                override fun onPageSelected(position: Int) {
                    pageIndicatorView.selection = position
                    var current = vp_home_navi_act_frag_pager.adapter!!.instantiateItem(vp_home_navi_act_frag_pager, position)
                }

                override fun onPageScrollStateChanged(state: Int) {/*empty*/
                }
            })
        }
    }
}