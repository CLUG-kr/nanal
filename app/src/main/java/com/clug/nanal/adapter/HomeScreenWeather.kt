package com.clug.nanal.adapter

class HomeScreenWeather(){
    private var temperature: String? = null
    private var highest: String? = null
    private var lowest: String? = null

    fun setTemperature(temperature: String) {
        this.temperature = temperature
    }

    fun setHighest(highest: String) {
        this.highest = highest
    }

    fun setLowest(lowest: String) {
        this.lowest = lowest
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
}