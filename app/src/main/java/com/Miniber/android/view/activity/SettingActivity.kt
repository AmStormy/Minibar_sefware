package com.Miniber.android.view.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.Miniber.android.R
import com.Miniber.android.view.BaseActivity
import com.afollestad.materialdialogs.MaterialDialog
import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.activity_setting.view.*


/**
 * Created by chaiwut on 11/12/17.
 */
class SettingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        defineView(savedInstanceState)
        defineSubscribe()
        defineAction()
    }

    @SuppressLint("SetTextI18n")
    override fun defineView(savedInstanceState: Bundle?) {
        toolbar.toolbar_title.setText(R.string.settings)
        toolbar.title = ""
        toolbar.navigationIcon = ContextCompat.getDrawable(this,R.drawable.ic_arrow_back)
        setSupportActionBar(toolbar)

        if (currentLanguage.language == "en"){
            tv_language.text = resources.getString(R.string.english)
        }else{
            tv_language.text = resources.getString(R.string.thai)
        }

        val versionName = this.packageManager.getPackageInfo(this.packageName, 0).versionName
        val versionCode = this.packageManager.getPackageInfo(this.packageName, 0).versionCode
        tv_version.text = "$versionName($versionCode)"
    }

    override fun defineAction() {
        toolbar.setNavigationOnClickListener {
            finish()
        }

        layout_language.setOnClickListener {
            MaterialDialog.Builder(this)
                    .title(R.string.change_language)
                    .items(R.array.languages)
                    .itemsCallbackSingleChoice(if (currentLanguage.language == "en") 0 else 1, { _, _, _, text ->
                        when(text){
                            "English" -> {
                                setLanguage("en")
                                tv_language.text = resources.getString(R.string.english)
                            }
                            "ภาษาไทย" -> {
                                setLanguage("th")
                                tv_language.text = resources.getString(R.string.thai)
                            }
                        }
                        true
                    })
                    .positiveText(R.string.confirm)
                    .show()
        }
    }

    override fun defineSubscribe() {
    }

}