package com.Miniber.android.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import com.Miniber.android.R
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.Property
import com.Miniber.android.model.Response
import com.Miniber.android.view.BaseActivity
import com.Miniber.android.viewmodel.VerifyViewModel
import kotlinx.android.synthetic.main.activity_verify.*
import timber.log.Timber


/**
 * Created by chaiwut on 6/12/17.
 */
class VerifyActivity: BaseActivity(){

    var viewModel: VerifyViewModel? = null
    lateinit var editText:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verify)

        defineView()
        defineAction()
        defineSubscribe()
    }

    override fun defineView() {
        viewModel = ViewModelProviders.of(this).get(VerifyViewModel::class.java)

        editText = findViewById<EditText>(R.id.et_property_code)
    }

    override fun defineAction() {
        btn_submit.setOnClickListener{
            showLoadingDialog(this,false)
            viewModel?.verifyCode(editText.text.toString())
        }
    }

    override fun defineSubscribe() {
        viewModel!!.liveDataVerifyStatus().observe(this, Observer<Response> { it ->

            if(it!!.success){
                Timber.d(it.payload.to(Property::class.java).toString())
                PreferenceHelper(this).addSessionProperty(it.payload as Property)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }else{
                hideLoadingDialog()
                Timber.d(it.message)
            }
        })
    }
}