package com.Miniber.android.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.Miniber.android.R
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.User
import com.Miniber.android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_pin.*
import timber.log.Timber


/**
* Created by chaiwut on 5/12/17.
*/
class PinActivity: BaseActivity(){

    companion object {
        private var pinSize: Int = 0
        lateinit var viewModel: PinViewModel
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        defineView(savedInstanceState)
        defineAction()
        defineSubscribe()
    }

    override fun defineView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null){
            viewModel = ViewModelProviders.of(this).get(PinViewModel::class.java)
            viewModel.resetPin()
        }
    }

    override fun defineAction(){
        //Keypad Event
        val propertyCode = PreferenceHelper(this).getSessionProperty().code.toString()
        btn_0.setOnClickListener { viewModel.addPin(0,propertyCode) }
        btn_1.setOnClickListener { viewModel.addPin(1,propertyCode) }
        btn_2.setOnClickListener { viewModel.addPin(2,propertyCode) }
        btn_3.setOnClickListener { viewModel.addPin(3,propertyCode) }
        btn_4.setOnClickListener { viewModel.addPin(4,propertyCode) }
        btn_5.setOnClickListener { viewModel.addPin(5,propertyCode) }
        btn_6.setOnClickListener { viewModel.addPin(6,propertyCode) }
        btn_7.setOnClickListener { viewModel.addPin(7,propertyCode) }
        btn_8.setOnClickListener { viewModel.addPin(8,propertyCode) }
        btn_9.setOnClickListener { viewModel.addPin(9,propertyCode) }
        btn_del.setOnClickListener { viewModel.removePin()}

    }

    override fun defineSubscribe() {
        viewModel.liveDataPinSize().observe(this, Observer<Int> { it ->
            pinSize = it!!
            updateCircle()
        })

        viewModel.liveDataLoading().observe(this, Observer<Boolean> { it ->
            if(it!!){
                mDialog?.showLoadingDialog(false)
            }else{
                mDialog?.hideLoadingDialog()
            }
        })

        viewModel.liveDataUser().observe(this, Observer<User> { it ->
            if (it != null && it.uid != "") {
                PreferenceHelper(this).addSessionUser(it)
                ViewModelProviders.of(this).get(MainViewModel::class.java).setUser(it)
                finish()
            }
        })
    }

    private fun updateCircle() {
        var selected: Boolean
        for (i in 1..6) {
            selected = false
            if (i <= pinSize) {
                selected = true
            }
            when (i) {
                1 -> {
                    btn_circle1.isSelected = selected
                    btn_circle2.isSelected = selected
                    btn_circle3.isSelected = selected
                    btn_circle4.isSelected = selected
                    btn_circle5.isSelected = selected
                    btn_circle6.isSelected = selected
                }
                2 -> {
                    btn_circle2.isSelected = selected
                    btn_circle3.isSelected = selected
                    btn_circle4.isSelected = selected
                    btn_circle5.isSelected = selected
                    btn_circle6.isSelected = selected
                }
                3 -> {
                    btn_circle3.isSelected = selected
                    btn_circle4.isSelected = selected
                    btn_circle5.isSelected = selected
                    btn_circle6.isSelected = selected
                }
                4 -> {
                    btn_circle4.isSelected = selected
                    btn_circle5.isSelected = selected
                    btn_circle6.isSelected = selected
                }
                5 -> {
                    btn_circle5.isSelected = selected
                    btn_circle6.isSelected = selected
                }
                6 -> btn_circle6.isSelected = selected
            }
        }
    }

}