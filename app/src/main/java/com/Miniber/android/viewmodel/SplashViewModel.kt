package com.Miniber.android.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import java.util.*

/**
* Created by chaiwut on 2/12/17.
*/
class SplashViewModel : ViewModel() {

    companion object {
        private val delay = 0
        private val period = 1000
        private val stop = 3000
    }
    private val mLoaded = MutableLiveData<Boolean>()

    init {
        var startTime = 0
        val timer = Timer()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                startTime += period
                if(startTime >= stop){
                    mLoaded.postValue(true)
                    timer.cancel()
                }
            }
        }, delay.toLong(), period.toLong())
    }

    fun loading(): LiveData<Boolean> {
        return mLoaded
    }


}