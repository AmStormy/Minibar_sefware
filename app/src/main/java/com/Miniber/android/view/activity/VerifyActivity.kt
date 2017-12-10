package com.Miniber.android.view.activity

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import com.Miniber.android.R
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.Property
import com.Miniber.android.model.Response
import com.Miniber.android.view.BaseActivity
import com.afollestad.materialdialogs.MaterialDialog
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
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.ic_public)
        toolbar.overflowIcon = drawable
        toolbar.title = resources.getString(R.string.verification)
        setSupportActionBar(toolbar)
        viewModel = ViewModelProviders.of(this).get(VerifyViewModel::class.java)
        val view: View = if (currentFocus == null) View(this) else currentFocus
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        editText = findViewById(R.id.et_property_code)
    }

    override fun defineAction() {
        btn_submit.setOnClickListener{
            if(editText.text.toString() != ""){
                showLoadingDialog(this,false)
                viewModel?.verifyCode(editText.text.toString())
            }
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

                MaterialDialog.Builder(this)
                        .title(R.string.code_fail)
                        .content(R.string.code_not_found)
                        .positiveText(R.string.close)
                        .show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.language, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_menu_english -> {
                setLanguage("en")
                return true
            }
            R.id.item_menu_thai -> {
                setLanguage("th")
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}