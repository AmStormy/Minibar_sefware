package com.Miniber.android.view

import android.widget.Toast
import com.Miniber.android.BuildConfig

/**
* Created by chaiwut on 2 Dec 17.
*/
open class BaseFragment : android.support.v4.app.Fragment() {

    fun showToast(text: String){
        if(BuildConfig.DEBUG){
            Toast.makeText(activity,text,Toast.LENGTH_SHORT).show()
        }
    }
}