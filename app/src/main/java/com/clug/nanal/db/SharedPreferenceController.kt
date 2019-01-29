package com.clug.nanal.db

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceController {
    private val USER_NAME: String? = null
    private val USER_SEX: String? = null
    private val USER_TEMPO: String? = null
    private val USER_LOCATION: String? = null

    private var pref: SharedPreferences? = null

    fun getPref(context: Context){
        if(pref==null){
            pref=context.getSharedPreferences("data", Context.MODE_PRIVATE)
        }
    }

    fun setUserName(ctx: Context, input_name: String) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_NAME, input_name)
        editor.commit()
    }

    fun setUserSex(ctx: Context, input_sex: String) {                            //비밀번호 설정
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_SEX, input_sex)
        editor.commit()
    }

    fun setUserTempo(ctx: Context, input_tempo: Int) {                            //비밀번호 설정
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putInt(USER_TEMPO, input_tempo)
        editor.commit()
    }

    fun setUserLocationX(ctx: Context, input_location: String) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_LOCATION, input_location)
        editor.commit()
    }

    fun getUserName(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_NAME, "")
    }

    fun getUserSex(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_SEX, "")
    }

    fun getUserTempo(ctx: Context): Int {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getInt(USER_TEMPO, -1)
    }

    fun getUserLocation(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_LOCATION, "")
    }

    fun clearUserSharedPreferences(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }
}