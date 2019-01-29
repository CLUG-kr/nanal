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
        dataList.add(AddViewData("날씨 온도", ""))
        dataList.add(AddViewData("미세먼지", ""))

        addViewRecyclerViewAdapter = AddViewRecyclerViewAdapter(activity!!, dataList)
        rv_addview_list.adapter=addViewRecyclerViewAdapter
        rv_addview_list.layoutManager = GridLayoutManager(activity!!, 2)
    }
}