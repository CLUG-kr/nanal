package com.clug.nanal.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.clug.nanal.R
import com.clug.nanal.adapter.MainFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configureBottomNavigation()
    }

    private fun configureBottomNavigation() {
        vp_main_navi_act_frag_pager.adapter = MainFragmentStatePagerAdapter(supportFragmentManager, 4)
        vp_main_navi_act_frag_pager.offscreenPageLimit = 4

        // ViewPager와 Tablayout을 엮어줍니다!
        tl_main_navi_act_bottom_menu.setupWithViewPager(vp_main_navi_act_frag_pager)

        //TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.main_navigation_tab, null, false)

        //탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_main_navi_act_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.btn_main_navi_home_tab) as ImageView
        tl_main_navi_act_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.btn_main_navi_location_tab) as ImageView
        tl_main_navi_act_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.btn_main_navi_addview_tab) as ImageView
        tl_main_navi_act_bottom_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.btn_main_navi_setting_tab) as ImageView
    }
}
