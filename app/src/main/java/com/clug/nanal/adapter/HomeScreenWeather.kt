package com.clug.nanal.adapter

import android.R.attr.y
import android.R.attr.x
import android.R.attr.y
import android.R.attr.x





class HomeScreenWeather(){
    private var temperature: String? = null
    private var highest: String? = null
    private var lowest: String? = null
    private var sky = -1
    private var city: String? = null
    private var borough: String? = null
    private var dong: String? = null
    private var x: String? = null
    private var y: String? = null


    fun setTemperature(temperature: String) {
        this.temperature = temperature
    }

    fun setHighest(highest: String) {
        this.highest = highest
    }

    fun setLowest(lowest: String) {
        this.lowest = lowest
    }

    fun setSky(sky: Int) {
        this.sky = sky
    }

    fun setCity(city: String) {
        this.city = city
    }

    fun setBorough(borough: String) {
        this.borough = borough
    }

    fun setDong(dong: String) {
        this.dong = dong
    }

    fun setX(x: String) {
        this.x = x
    }

    fun setY(y: String) {
        this.y = y
    }


    fun getTemperature(): String? {
        return temperature
    }

    fun getHighest(): String? {
        return highest
    }

    fun getLowest(): String? {
        return lowest
    }

    fun getSky(): Int {
        return sky
    }

    fun getCity(): String {
        return city!!
    }

    fun getBorough(): String {
        return borough!!
    }

    fun getDong(): String {
        return dong!!
    }

    fun getX(): String {
        return x!!
    }

    fun getY(): String {
        return y!!
    }
}