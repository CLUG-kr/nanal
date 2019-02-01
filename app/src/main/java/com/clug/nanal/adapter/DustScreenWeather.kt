package com.clug.nanal.adapter


class DustScreenWeather() {
    private var city: String? = null
    private var borough: String? = null
    private var dong: String? = null
    private var tmx: String? = null
    private var tmy: String? = null
    private var stationName: String? = null
    private var pm10Value = 0
    private var pm25Value = 0
    private var pm10Degree = 0
    private var pm25Degree = 0

    fun init(city: String, borough: String, dong: String) {
        this.city = city
        this.borough = borough
        this.dong = dong
    }

    fun setTmx(tmx: String) {
        this.tmx = tmx
    }

    fun setTmy(tmy: String) {
        this.tmy = tmy
    }

    fun setStationName(stationName: String) {
        this.stationName = stationName
    }

    fun setPm10Value(pm10Value: Int) {
        this.pm10Value = pm10Value
    }

    fun setPm25Value(pm25Value: Int) {
        this.pm25Value = pm25Value
    }

    fun setPm10Degree(pm10Degree: Int) {
        this.pm10Degree = pm10Degree
    }

    fun setPm25Degree(pm25Degree: Int) {
        this.pm25Degree = pm25Degree
    }

    fun getCity(): String? {
        return city
    }

    fun getBorough(): String? {
        return borough
    }

    fun getDong(): String? {
        return dong
    }

    fun getTmx(): String? {
        return tmx
    }

    fun getTmy(): String? {
        return tmy
    }

    fun getStationName(): String? {
        return stationName
    }

    fun getPm10Value(): Int {
        return pm10Value
    }

    fun getPm25Value(): Int {
        return pm25Value
    }

    fun getPm10Degree(): Int {
        return pm10Degree
    }

    fun getPm25Degree(): Int {
        return pm25Degree
    }

}