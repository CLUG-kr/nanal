package com.clug.nanal.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.adapter.AddViewRecyclerViewAdapter
import com.clug.nanal.data.AddViewData
import com.clug.nanal.db.SharedPreferenceController
import kotlinx.android.synthetic.main.fragment_addview.*

class AddviewFragment: Fragment(){

    lateinit var addViewRecyclerViewAdapter: AddViewRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_addview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView(){
        var dataList : ArrayList<AddViewData> = ArrayList()
        dataList.add(AddViewData("날씨 온도", "", setExist(SharedPreferenceController.getHome1(activity!!))))
        dataList.add(AddViewData("미세먼지", "https://github.com/CLUG-kr/nanal/blob/master/mise/mise.jpg?raw=true",setExist(SharedPreferenceController.getHome2(activity!!))))
        dataList.add(AddViewData("이번주 날씨", "",setExist(SharedPreferenceController.getHome3(activity!!))))
        dataList.add(AddViewData("등산", "",setExist(SharedPreferenceController.getHome4(activity!!))))
        dataList.add(AddViewData("낚시", "",setExist(SharedPreferenceController.getHome5(activity!!))))
        //TODO 각 뷰의 사진 인터넷에 올리고 이미지 링크 걸기

        addViewRecyclerViewAdapter = AddViewRecyclerViewAdapter(activity!!, dataList)
        rv_addview_list.adapter=addViewRecyclerViewAdapter
        rv_addview_list.layoutManager = GridLayoutManager(activity!!, 2)
    }

    private fun setExist(input: String) : Boolean{
        return input.equals("유")
    }
}