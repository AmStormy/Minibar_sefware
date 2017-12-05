package com.Miniber.android.helper

import android.support.annotation.Nullable
import com.crashlytics.android.Crashlytics
import android.util.Log
import timber.log.Timber

/**
* Created by chaiwut on 6/12/17.
*/
class CrashlyticsTree : Timber.Tree() {

    override fun log(priority: Int, @Nullable tag: String, @Nullable message: String, @Nullable t: Throwable?) {

        // ignore Log.VERBOSE, Log.DEBUG, Log.INFO
        if (priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
            return
        }

        // command Crashlytics send data
        Crashlytics.setInt(CRASHLYTICS_KEY_PRIORITY, priority)
        Crashlytics.setString(CRASHLYTICS_KEY_TAG, tag)
        Crashlytics.setString(CRASHLYTICS_KEY_MESSAGE, message)

        if (t == null) {
            Crashlytics.logException(Exception(message))
        } else {
            Crashlytics.logException(t)
        }
    }

    companion object {
        private val CRASHLYTICS_KEY_PRIORITY = "priority"
        private val CRASHLYTICS_KEY_TAG = "tag"
        private val CRASHLYTICS_KEY_MESSAGE = "message"
    }
}