package com.Miniber.android.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import com.Miniber.android.R
import android.os.Bundle
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.view.BaseActivity

/**
 * Created by Chaiwut on 2/12/2017
 */
class SplashActivity : BaseActivity(){

    companion object {
        private var viewModel: SplashViewModel? = null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        defineView()
        defineAction()
        defineSubscribe()
    }

    override fun defineView() {
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
    }

    override fun defineAction() {

    }

    override fun defineSubscribe() {
        viewModel!!.loading().observe(this, Observer<Boolean> { it ->
            if(it!!){
                if(PreferenceHelper(this).isPropertyRegister()){
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }else{
                    startActivity(Intent(this, VerifyActivity::class.java))
                    finish()
                }
            }
        })
    }
}