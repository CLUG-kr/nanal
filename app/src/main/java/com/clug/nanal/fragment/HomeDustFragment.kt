package com.clug.nanal.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.clug.nanal.R
import com.clug.nanal.R.drawable.img_very_satisfied
import kotlinx.android.synthetic.main.fragment_homedust.*

class HomeDustFragment : Fragment() {

    var faceDegree: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_homedust, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setDegree()

        setFace()
    }

    fun setDegree(){
        //민지가 미세먼지, 초미세먼지 받아와서 좋음 보통 나쁨 마다 가중치를 0 1 2 3 로 해서 다 더한걸
        //뷰에 각각 값을 띄우고 faceDegree값 변환
    }

    fun setFace() {
        if (faceDegree == 0) {
            img_homedust_face.setImageResource(R.drawable.img_very_satisfied)
        } else if (faceDegree == 1) {
            img_homedust_face.setImageResource(R.drawable.img_satisfied)
        } else if (faceDegree == 2) {
            img_homedust_face.setImageResource(R.drawable.img_neutral)
        } else if (faceDegree == 3) {
            img_homedust_face.setImageResource(R.drawable.img_dissatisfied)
        } else {
            img_homedust_face.setImageResource(R.drawable.img_very_dissatisfied)
        }
    }
}
