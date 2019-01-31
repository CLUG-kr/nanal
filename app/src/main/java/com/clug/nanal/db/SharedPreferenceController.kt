package com.clug.nanal.db

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import org.jetbrains.anko.db.INTEGER

object SharedPreferenceController {
    private val USER_NAME: String? = "user_name"
    private val USER_SEX: String? ="user_sex"
    private val USER_TEMPO: String? = "user_tempo"

    private val USER_LOCATION_LARGE: String? = "user_location_large"
    private val USER_LOCATION_MEDIUM: String? = "user_location_medium"
    private val USER_LOCATION_SMALL: String? = "user_location_small"
    private val HOME1 : String = "home1"
    private val HOME2 : String = "home2"
    private val HOME3 : String = "home3"
    private val HOME4 : String = "home4"
    private val HOME5 : String = "home5"
    private val COUNT : String = "count"


    private var pref: SharedPreferences? = null

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

    fun setUserTempo(ctx: Context, input_tempo: F) {                            //비밀번호 설정
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putFloat(USER_TEMPO, input_tempo)
        editor.commit()
    }

    fun setUserLocationLarge(ctx: Context, input_location: String) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_LOCATION_LARGE, input_location)
        editor.commit()
    }

    fun setUserLocationMedium(ctx: Context, input_location: String) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_LOCATION_MEDIUM, input_location)
        editor.commit()
    }

    fun setUserLocationSmall(ctx: Context, input_location: String) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_LOCATION_SMALL, input_location)
        editor.commit()
    }

    fun setHome1True(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME1, "유")
        editor.commit()
    }

    fun setHome1False(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME1, "무")
        editor.commit()
    }

    fun setHome2True(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME2, "유")
        editor.commit()
    }

    fun setHome2False(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME2, "무")
        editor.commit()
    }

    fun setHome3True(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME3, "유")
        editor.commit()
    }

    fun setHome3False(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME3, "무")
        editor.commit()
    }

    fun setHome4True(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME4, "유")
        editor.commit()
    }

    fun setHome4False(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME4, "무")
        editor.commit()
    }

    fun setHome5True(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME5, "유")
        editor.commit()
    }

    fun setHome5False(ctx: Context) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putString(HOME5, "무")
        editor.commit()
    }

    fun setCount(ctx: Context, input_int : Int) {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preferences.edit()
        editor.putInt(COUNT, input_int)
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
        return preferences.getFloat(USER_TEMPO, 22.0)
    }

    fun getUserLocationLarge(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_LOCATION_LARGE, "")
    }

    fun getUserLocationMedium(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_LOCATION_MEDIUM, "")
    }

    fun getUserLocationSmall(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(USER_LOCATION_SMALL, "")
    }

    fun getHome1(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(HOME1, "")
    }

    fun getHome2(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(HOME2, "")
    }

    fun getHome3(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(HOME3, "")
    }

    fun getHome4(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(HOME4, "")
    }

    fun getHome5(ctx: Context): String {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getString(HOME5, "")
    }

    fun getCount(ctx: Context): Int {
        val preferences: SharedPreferences = ctx.getSharedPreferences("data", Context.MODE_PRIVATE)
        return preferences.getInt(COUNT, 0)
    }

    fun clearUserSharedPreferences(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }
}