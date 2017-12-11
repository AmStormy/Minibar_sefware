package com.Miniber.android.view.activity

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.*
import android.util.Log
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.Property
import com.Miniber.android.model.Response
import com.Miniber.android.model.User
import com.Miniber.android.view.BaseViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber
import java.util.*

/**
 * Created by chaiwut on 2/12/17.
 */
class PinViewModel : BaseViewModel() {

    companion object {
        val mLoading = MutableLiveData<Boolean>()
        val mUser = MutableLiveData<User>()
        val mPinListSize = MutableLiveData<Int>()
        val mPinLists: MutableList<Int>? = arrayListOf()
    }

    private val db = FirebaseFirestore.getInstance()

    init {
        resetPinLists()
    }

    fun resetPinLists(){
        mPinLists?.clear()
    }

    fun resetUser(){
        mUser.postValue(User(""))
    }

    fun addPin(number: Int, propertCode: String){
        if(mPinLists!!.size<6){
            mPinLists.add(number)
            mPinListSize.postValue(mPinLists.size)
            if(mPinLists.size == 6){

                var passcode = ""
                for (it in mPinLists){
                    passcode += it.toString()
                }

                mLoading.postValue(true)
                val docRef = db.collection("users").document(propertCode).collection("users")
                docRef.get().addOnSuccessListener { collectionSnapshot ->
                    if(!collectionSnapshot.documents.isEmpty()){
                        for(document: DocumentSnapshot in collectionSnapshot.documents){
                            if(document.get("passcode") == passcode){
                                mUser.postValue(document.toObject(User::class.java))
                            }
                        }
                        resetPin()
                    }else{
                        resetPin()
                    }
                }.addOnFailureListener{ e ->
                    resetPin()
                }
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
        mLoading.postValue(false)
    }

    fun liveDataPinSize(): LiveData<Int> {
        return mPinListSize
    }

    fun liveDataLoading(): LiveData<Boolean> {
        return mLoading
    }

    fun liveDataUser(): LiveData<User> {
        return mUser
    }

    fun DummyCallApi(){
        var startTime = 0
        val timer = Timer()
        timer.scheduleAtFixedRate(object: TimerTask() {
            override fun run() {
                startTime += 1000
                if(startTime >= 2000){
                    resetPin()
                    timer.cancel()
                }
            }
        }, 0, 1000)
    }
}