package com.Miniber.android.view

import android.arch.lifecycle.ViewModel
import timber.log.Timber


/**
* Created by chaiwut on 2/12/17.
*/

abstract class BaseViewModel : ViewModel(){

    override fun onCleared() {
        super.onCleared()
        Timber.d(javaClass.simpleName+ " onCleared")
    }
}
