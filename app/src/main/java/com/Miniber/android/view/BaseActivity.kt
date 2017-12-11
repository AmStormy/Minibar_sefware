package com.Miniber.android.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Debug
import android.support.annotation.Nullable
import android.widget.Toast
import com.Miniber.android.BuildConfig
import com.Miniber.android.helper.DialogHelper
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import timber.log.Timber


/**
* Created by chaiwut on 2/12/17.
*/

@SuppressLint("Registered")
abstract class BaseActivity : LocalizationActivity(){

    val TAG = this.javaClass.simpleName
    var mDialog: DialogHelper? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG)
        Timber.d("onCreate and savedInstanceState is " + savedInstanceState)
        mDialog = DialogHelper(this)
        setDefaultLanguage("th")
    }

    //Base functions
    abstract fun defineView(savedInstanceState: Bundle?)

    abstract fun defineAction()

    abstract fun defineSubscribe()

    //Log events
    override fun onStart() {
        super.onStart()
        Timber.tag(TAG)
        Timber.d("onStart")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG)
        Timber.d("onResume")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG)
        Timber.d("onPause")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG)
        Timber.d("onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        mDialog?.hideLoadingDialog()
        Timber.tag(TAG)
        Timber.d("onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Timber.tag(TAG)
        Timber.d("onSaveInstanceState: " + outState!!)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        Timber.tag(TAG)
        Timber.d("onRestoreInstanceState: " + savedInstanceState)
    }

    fun showToast(text: String){
        if(BuildConfig.DEBUG){
            Toast.makeText(this,text, Toast.LENGTH_SHORT).show()
        }
    }

}
