package com.Miniber.android.helper

import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import android.text.TextUtils
import com.Miniber.android.model.Property


/**
 * Created by chaiwut on 6/12/17.
 */
class PreferenceHelper(private val context: Context) {

    val PREFERENCE_COMPANY_CODE = "company_code"
    val PREFERENCE_COMPANY_NAME = "company_name"
    val PREFERENCE_COMPANY_LOGO = "company_logo"
    val PREFERENCE_COMPANY_PHOTO = "company_photo"

    val sharedPreferences: SharedPreferences?
        get() = PreferenceManager.getDefaultSharedPreferences(context)

    fun shouldPersist(): Boolean {
        return sharedPreferences != null
    }

    fun getSessionProperty(): Property{
        return Property(getPersistedString(PREFERENCE_COMPANY_CODE,"").toString(),
                getPersistedString(PREFERENCE_COMPANY_NAME,"").toString(),
                getPersistedString(PREFERENCE_COMPANY_LOGO,"").toString(),
                getPersistedString(PREFERENCE_COMPANY_PHOTO,"").toString())
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

    fun getPersistedString(key: String, defaultValue: String?): String? {
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

    /**
     * Property sessions
     * */

    fun isPropertyRegister(): Boolean {
        return sharedPreferences!!.getString(PREFERENCE_COMPANY_CODE, "") != ""
    }

    fun addSessionProperty(property: Property){
        property.code?.let { persistString(PREFERENCE_COMPANY_CODE, it) }
        property.name.let { persistString(PREFERENCE_COMPANY_NAME, it) }
        property.logo.let { persistString(PREFERENCE_COMPANY_LOGO, it) }
        property.photo.let { persistString(PREFERENCE_COMPANY_PHOTO, it) }
    }

    fun resetSessionProperty(){
        persistString(PREFERENCE_COMPANY_CODE, "")
        persistString(PREFERENCE_COMPANY_NAME, "")
        persistString(PREFERENCE_COMPANY_LOGO, "")
        persistString(PREFERENCE_COMPANY_PHOTO, "")
    }
}