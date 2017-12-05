package com.Miniber.android.helper

import android.content.Context
import android.R.id.edit
import android.content.SharedPreferences
import android.support.annotation.StringRes
import android.R.id.edit





/**
* Created by chaiwut on 6/12/17.
*/

class InitSetting private constructor(private val context: Context) {
    private var preferenceHelper: PreferenceHelper? = null
    private var isFirstRun = true

    init {
        preferenceHelper = PreferenceHelper(context)
    }

    fun persistString(key: String, value: String): InitSetting {
        if (isFirstRun) preferenceHelper!!.persistString(key, value)
        return this
    }

    fun persistString(@StringRes stringId: Int, value: String): InitSetting {
        return persistString(getString(stringId), value)
    }


    private fun getString(@StringRes stringId: Int): String {
        return context.resources.getString(stringId)
    }

    fun ifFirstRunApplication(): InitSetting {
        isFirstRun = preferenceHelper!!.getPersistedBoolean(FIRST_RUN_APPLICATION, true)
        if (isFirstRun) {
            val editor = preferenceHelper!!.sharedPreferences?.edit()
            editor?.putBoolean(FIRST_RUN_APPLICATION, false)
            editor?.apply()
        }
        return this
    }

    companion object {
        val FIRST_RUN_APPLICATION = "first_run_application"

        fun init(context: Context): InitSetting {
            return InitSetting(context)
        }
    }
}