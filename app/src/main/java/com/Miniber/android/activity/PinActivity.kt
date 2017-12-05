package com.Miniber.android.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.Miniber.android.R
import kotlinx.android.synthetic.main.activity_pin.*


/**
* Created by chaiwut on 5/12/17.
*/
class PinActivity: _BaseActivity(){

    companion object {
        private var viewModel: PinViewModel? = null
        private var pinSize: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        defineView()
        defineAction()
    }

    override fun defineView(){
        viewModel = ViewModelProviders.of(this).get(PinViewModel::class.java)
        subScriptions(viewModel!!)
    }

    override fun defineAction(){
        //Keypad Event
        btn_0.setOnClickListener { viewModel?.addPin(0) }
        btn_1.setOnClickListener { viewModel?.addPin(1) }
        btn_2.setOnClickListener { viewModel?.addPin(2) }
        btn_3.setOnClickListener { viewModel?.addPin(3) }
        btn_4.setOnClickListener { viewModel?.addPin(4) }
        btn_5.setOnClickListener { viewModel?.addPin(5) }
        btn_6.setOnClickListener { viewModel?.addPin(6) }
        btn_7.setOnClickListener { viewModel?.addPin(7) }
        btn_8.setOnClickListener { viewModel?.addPin(8) }
        btn_9.setOnClickListener { viewModel?.addPin(9) }
        btn_del.setOnClickListener { viewModel?.removePin()}

    }


    fun subScriptions(viewModel: PinViewModel){
        viewModel.liveDataPinSize().observe(this, Observer<Int> { it ->
            pinSize = it!!
            updateCircle()
        })

        viewModel.liveDataCheckPin().observe(this, Observer<Boolean> { it ->
            if(it!!){
                showLoadingDialog(this,false)
            }else{
                hideLoadingDialog()
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