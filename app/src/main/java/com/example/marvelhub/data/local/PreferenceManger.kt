package com.example.marvelhub.data.local

import android.content.Context
import android.content.SharedPreferences
import com.example.marvelhub.utils.Constants.BASE_OFFSET
import com.example.marvelhub.utils.Constants.OFFSET_PREF_KEY
import com.example.marvelhub.utils.Constants.PREF_NAME
import com.example.marvelhub.utils.Constants.PRIVATE_MODE
import javax.inject.Inject

class PreferenceManager constructor(ctx:Context) {
    private val sharedPref: SharedPreferences = ctx.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
    fun setOffsetValue(offset :Int){
        val editor = sharedPref.edit()
        editor.putInt(OFFSET_PREF_KEY, offset)
        editor.apply()
    }
    fun getOffsetValue() = sharedPref.getInt(OFFSET_PREF_KEY, BASE_OFFSET)
    fun releaseOffsetValue(): Unit =sharedPref.edit().clear().apply()
}