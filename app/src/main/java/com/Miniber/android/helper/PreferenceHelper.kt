package com.Miniber.android.helper

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.text.TextUtils



/**
 * Created by chaiwut on 6/12/17.
 */
class PreferenceHelper(private val context: Context) {


    val sharedPreferences: SharedPreferences?
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun shouldPersist(): Boolean {
        return sharedPreferences != null
    }


    fun persistString(key: String, value: String): Boolean {
        if (shouldPersist()) {
            // Shouldn't store null
            if (TextUtils.equals(value, getPersistedString(key, null))) {
                // It's already there, so the same as persisting
                return true
            }

            val editor = sharedPreferences!!.edit()
            editor.putString(key, value)
            editor.apply()
            return true
        }
        return false
    }

    fun getPersistedString(key: String,
                           defaultValue: String?): String? {
        return if (!shouldPersist()) {
            defaultValue
        } else sharedPreferences!!.getString(key, defaultValue)
    }

    fun persistedBoolean(key: String,
                         value: Boolean,
                         shouldPersist: Boolean): Boolean {
        if (shouldPersist) {

            if (value == getPersistedBoolean(key, !value)) {
                // It's already there, so the same as persisting
                return true
            }
            val editor = sharedPreferences!!.edit()
            editor.putBoolean(key, value)
            editor.apply()
            return true
        }
        return false
    }


    fun getPersistedBoolean(key: String,
                            defaultValue: Boolean): Boolean {
        return if (!shouldPersist()) {
            defaultValue
        } else sharedPreferences!!.getBoolean(key, defaultValue)
    }

}