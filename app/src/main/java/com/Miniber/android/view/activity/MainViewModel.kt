package com.Miniber.android.view.activity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.Miniber.android.model.User
import com.Miniber.android.view.BaseViewModel

/**
 * Created by chaiwut on 2/12/17.
 */
class MainViewModel : BaseViewModel() {

    companion object {
        val TAG = "MainViewModel"
        val mUser = MutableLiveData<User>()
    }

    init {
        Log.d(TAG,"created.")
    }

    fun setUser(user: User){
        mUser.postValue(user)
    }

    fun liveDataUser(): LiveData<User> {
        return mUser
    }

}