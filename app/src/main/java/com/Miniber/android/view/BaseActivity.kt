package com.Miniber.android.view

import android.annotation.SuppressLint
import android.app.Dialog
import com.Miniber.android.R
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.Window
import timber.log.Timber


/**
* Created by chaiwut on 2/12/17.
*/

@SuppressLint("Registered")
abstract class BaseActivity : AppCompatActivity(){

    val TAG = this.javaClass.simpleName

    var mDialog: Dialog? = null

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.tag(TAG)
        Timber.d("onCreate: " + savedInstanceState)

        mDialog = Dialog(this)

    }

    //Base functions
    abstract fun defineView()

    abstract fun defineAction()

    abstract fun defineSubscribe()

    //Log events
    override fun onStart() {
        super.onStart()
        Timber.tag(TAG)
        Timber.d("onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Timber.tag(TAG)
        Timber.d("onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Timber.tag(TAG)
        Timber.d("onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Timber.tag(TAG)
        Timber.d("onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        hideLoadingDialog()
        Timber.tag(TAG)
        Timber.d("onDestroy: ")
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

    fun showLoadingDialog(context: Context, cancelAble: Boolean) {
        if(!mDialog!!.isShowing){
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_splash,null)
            mDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog!!.setContentView(view)
            mDialog!!.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog!!.setCancelable(cancelAble)
            mDialog!!.show()
        }
    }

    fun hideLoadingDialog() {
        if(mDialog != null && mDialog!!.isShowing){
            mDialog?.dismiss()
            mDialog = Dialog(this)
        }
    }
}
