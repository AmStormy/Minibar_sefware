package com.Miniber.android.view.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.transition.Transition
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import com.Miniber.android.R
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*
import kotlinx.android.synthetic.main.layout_app_nav_header_main.view.*
import timber.log.Timber
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defineView()
        defineAction()
        defineSubscribe()
    }

    override fun defineView() {
        val title = toolbar.findViewById<TextView>(R.id.toolbar_title)
        title.setText(R.string.app_name)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val property = PreferenceHelper(this).getSessionProperty()
        Timber.d("code = "+ property.code)
        Timber.d("name = "+ property.name)
        Timber.d("logo = "+ property.logo)
        Timber.d("photo = "+ property.photo)

        nav_view.getHeaderView(0).property_name.text = property.name
        if(property.logo != ""){
            Glide.with(this).load(property.logo).into(nav_view.getHeaderView(0).property_logo)
        }
        if(property.photo != ""){
            Glide.with(this).load(property.photo).into(object : SimpleTarget<Drawable>() {
                @SuppressLint("ObsoleteSdkInt")
                override fun onResourceReady(resource: Drawable?, transition: com.bumptech.glide.request.transition.Transition<in Drawable>?) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        nav_view.getHeaderView(0).property_photo.background = resource
                    }
                }
            })
        }


    }

    override fun defineAction() {
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
    }

    override fun defineSubscribe() {

    }

    override fun onBackPressed() {
        Log.d(TAG,"onBackPressed")
        hideLoadingDialog()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.activity_splash -> {
                startActivity(Intent(this, PinActivity::class.java))
//                finish()
//                showLoadingDialog(this,true)
            }
            R.id.menu_logout -> {
                PreferenceHelper(this).resetSessionProperty()
                startActivity(Intent(this,VerifyActivity::class.java))
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
