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

    private var pref: SharedPreferences? = null

    fun getPref(context: Context) {
        if (pref == null) {
            pref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
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
        return preferences.getInt(USER_TEMPO, 0)
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

    fun clearUserSharedPreferences(ctx: Context) {
        val preference: SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = preference.edit()
        editor.clear()
        editor.commit()
    }
}