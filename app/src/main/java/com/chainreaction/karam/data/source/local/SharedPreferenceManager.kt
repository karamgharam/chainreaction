package com.chainreaction.karam.data.source.local

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


class SharedPreferenceManager(context: Context) {


    //note
    //u can encrypt data and key

    private var pref: SharedPreferences
    private var editor: SharedPreferences.Editor
    private var _context: Context


    init {
        _context = context
        // Shared pref mode
        val privateMode = 0
        pref = _context.getSharedPreferences("chainReaction", privateMode)
        editor = pref.edit()
        editor.apply()
    }

    //get string from shared preferences
    private fun getPrefString(key: String): String {
        return pref.getString(key, "") ?: ""
    }

    //generic fun to save any type to shared preferences
    fun <T> savePref(value: T, key: String) {
        try {
            val gson = Gson()
            val json = gson.toJson(value)
            //put String
            editor.putString(key, json.toString())
            // commit changes
            editor.commit()
        } catch (_: Exception) {
        }
    }

    //generic fun to get ny type from shared preferences
    fun <T> getPref(key: String, objectClass: Class<T>): T? {
        var responseValue: T? = null
        try {
            val gson = Gson()
            val json = getPrefString(key)
            responseValue = gson.fromJson(json, objectClass)
        } catch (_: java.lang.Exception) {
        }
        return responseValue

    }
}