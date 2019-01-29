package com.clug.nanal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.clug.nanal.R
import com.clug.nanal.data.AddViewData

class AddViewRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<AddViewData>):RecyclerView.Adapter<AddViewRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_addview, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.name.text=dataList[position].name
        /*
        Glide.with(ctx)
                .setDefaultRequestOptions(requestOptions)
                .load(dataList[position].img_url)
                .thumbnail(0.5f)
                .into(holder.picture)
         */
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name : TextView = itemView.findViewById(R.id.tv_rv_item_addview) as TextView
        val picture : ImageView = itemView.findViewById(R.id.img_rv_item_addview) as ImageView
    }

    //TODO 이미 사용하고 있는 뷰는 다르게 표시 각각 클릭 했을 때 이벤트
}