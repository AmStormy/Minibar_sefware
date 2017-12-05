package com.Miniber.android.activity

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import java.util.*

/**
 * Created by chaiwut on 2/12/17.
 */
class PinViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        val TAG = "PinViewModel"
        val mCheckinPin = MutableLiveData<Boolean>()
        val mPinListSize = MutableLiveData<Int>()
        val mPinLists: MutableList<Int>? = arrayListOf()
    }

    init {
        Log.d(TAG,"created.")
    }

    fun addPin(number: Int){
        if(mPinLists!!.size<6){
            mPinLists.add(number)
            mPinListSize.postValue(mPinLists.size)
            if(mPinLists.size == 6){
                mCheckinPin.postValue(true)
                DummyCallApi()
            }
        }
    }

    fun removePin(){
        if(mPinLists!!.size>0){
            mPinLists.removeAt(mPinLists.size-1)
        }
        mPinListSize.postValue(mPinLists.size)
    }

    fun resetPin(){
        mPinLists!!.clear()
        mPinListSize.postValue(mPinLists.size)
        mCheckinPin.postValue(false)
    }

    fun liveDataPinSize(): LiveData<Int> {
        return mPinListSize
    }

    fun liveDataCheckPin(): LiveData<Boolean> {
        return mCheckinPin
    }

    fun DummyCallApi(){
        var startTime = 0
        val timer = Timer()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                startTime += 1000
                if(startTime >= 5000){
                    resetPin()
                    timer.cancel()
                }
            }
        }, 0, 1000)
    }
}