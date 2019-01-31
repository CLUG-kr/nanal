package com.clug.nanal.adapter

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.clug.nanal.R
import com.clug.nanal.data.AddViewData
import com.clug.nanal.db.SharedPreferenceController
import com.clug.nanal.fragment.AddviewDialogFragment

class AddViewRecyclerViewAdapter(val ctx: Context, val dataList: ArrayList<AddViewData>) : RecyclerView.Adapter<AddViewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_addview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.name.text = dataList[position].name

        if (dataList[position].img_url != "") {
            val requestOptions = RequestOptions()

            Glide.with(ctx)
                    .setDefaultRequestOptions(requestOptions)
                    .load(dataList[position].img_url)
                    .thumbnail(0.5f)
                    .into(holder.picture)
        }

        if (dataList[position].exist) {
            holder.outLine.visibility = View.VISIBLE
        }

        holder.btn.setOnClickListener {
            if (dataList[position].exist) {
                val addviewdialog: AddviewDialogFragment = AddviewDialogFragment()
                //addviewdialog.show(ctx.get,"addviewdialog")
                dataList[position].exist = false
                setExistFalse(position)
                SharedPreferenceController.setCount(ctx, SharedPreferenceController.getCount(ctx) - 1)
                holder.outLine.visibility = View.GONE
            } else {
                dataList[position].exist = true
                setExistTrue(position)
                SharedPreferenceController.setCount(ctx, SharedPreferenceController.getCount(ctx) + 1)
                holder.outLine.visibility = View.VISIBLE
            }
        }

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_rv_item_addview) as TextView
        val picture: ImageView = itemView.findViewById(R.id.img_rv_item_addview) as ImageView
        val outLine: ImageView = itemView.findViewById(R.id.img_rv_item_addview_select) as ImageView

        val btn: RelativeLayout = itemView.findViewById(R.id.layout_rv_item_addview) as RelativeLayout
    }

    fun setExistTrue(i: Int) {
        when (i) {
            0 -> SharedPreferenceController.setHome1True(ctx)
            1 -> SharedPreferenceController.setHome2True(ctx)
            2 -> SharedPreferenceController.setHome3True(ctx)
            3 -> SharedPreferenceController.setHome4True(ctx)
            4 -> SharedPreferenceController.setHome5True(ctx)
        }
    }

    fun setExistFalse(i: Int) {
        when (i) {
            0 -> SharedPreferenceController.setHome1False(ctx)
            1 -> SharedPreferenceController.setHome2False(ctx)
            2 -> SharedPreferenceController.setHome3False(ctx)
            3 -> SharedPreferenceController.setHome4False(ctx)
            4 -> SharedPreferenceController.setHome5False(ctx)
        }
    }

    //TODO 이미 사용하고 있는 뷰는 다르게 표시 각각 클릭 했을 때 이벤트
}