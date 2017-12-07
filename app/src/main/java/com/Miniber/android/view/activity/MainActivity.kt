package com.Miniber.android.view.activity

import com.Miniber.android.R
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.Property
import com.Miniber.android.view.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        defineView()
        defineAction()
        defineSubscribe()
    }

    override fun defineView() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val property = PreferenceHelper(this).getSessionProperty()

        val imageView = findViewById<ImageView>(R.id.imageView)
//        imageView.setImageURI(Uri.parse(property.photo))

    }

    override fun defineAction() {
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
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