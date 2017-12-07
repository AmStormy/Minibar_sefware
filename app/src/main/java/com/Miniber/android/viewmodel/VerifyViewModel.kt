package com.Miniber.android.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import android.support.v4.content.ContextCompat
import com.Miniber.android.R
import com.Miniber.android.model.Property
import com.Miniber.android.model.Response
import com.facebook.stetho.common.android.ResourcesUtil
import com.google.firebase.firestore.FirebaseFirestore
import timber.log.Timber


/**
* Created by chaiwut on 2/12/17.
*/
class VerifyViewModel : ViewModel() {

    val db = FirebaseFirestore.getInstance()
    val db_ref = "properties"

    private val mResponseProperty = MutableLiveData<Response>()

    fun verifyCode(code: String){
        Timber.d("mCode = "+code)
        val docRef = db.collection(db_ref).document(code)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            if(documentSnapshot.exists()){
                mResponseProperty.postValue(Response(true,"Success",documentSnapshot.toObject(Property::class.java)))
            }else{
                mResponseProperty.postValue(Response(false,Resources.getSystem().getString(R.string.not_data),""))
            }
        }.addOnFailureListener{ e ->
            mResponseProperty.postValue(Response(false,e.message.toString(),""))
        }
    }

    fun liveDataVerifyStatus(): LiveData<Response> {
        return mResponseProperty
    }


}