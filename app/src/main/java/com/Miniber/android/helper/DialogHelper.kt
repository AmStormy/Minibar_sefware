package com.Miniber.android.helper

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.Window
import com.Miniber.android.R

/**
 * Created by chaiwut on 5/12/17.
 */
open class DialogHelper (private val context: Context) {

    private var mDialog: Dialog = Dialog(context)

    open fun showLoadingDialog(cancelAble: Boolean) {
        if(!mDialog.isShowing){
            val view = LayoutInflater.from(context).inflate(R.layout.dialog_splash,null)
            mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mDialog.setContentView(view)
            mDialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            mDialog.setCancelable(cancelAble)
            mDialog.show()
        }
    }

    open fun hideLoadingDialog() {
        if(mDialog.isShowing){
            mDialog.dismiss()
            mDialog = Dialog(context)
        }
    }

}