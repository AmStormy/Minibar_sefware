package com.Miniber.android.view.activity

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import com.Miniber.android.R
import com.Miniber.android.helper.PreferenceHelper
import com.Miniber.android.model.Property
import com.Miniber.android.model.User
import com.Miniber.android.view.BaseActivity
import com.Miniber.android.view.fragment.RoomFragment
import com.Miniber.android.view.fragment.SignInFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.*
import kotlinx.android.synthetic.main.layout_app_bar_main.view.*
import kotlinx.android.synthetic.main.layout_app_nav_header_main.view.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    companion object {
        lateinit var viewModel : MainViewModel
    }

    private var property : Property? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        defineView(savedInstanceState)
        defineAction()
        defineSubscribe()
    }

    override fun defineView(savedInstanceState: Bundle?) {
        toolbar.toolbar_title.setText(R.string.app_name)
        toolbar.title = ""
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        // Setup Navigation View
        nav_view.setNavigationItemSelectedListener(this)

        property = PreferenceHelper(this).getSessionProperty()

        if(savedInstanceState == null){
            PreferenceHelper(this).resetSessionUser()
            viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.layout_fragment_container, SignInFragment.newInstance(property!!.name))
                    .commit()
            setNavigationViewUnSignIn(property!!)
        }
    }

    override fun defineAction() {

    }

    override fun defineSubscribe() {
        viewModel.liveDataUser().observe(this, Observer<User> { it ->
            if(it != null && it.uid != ""){
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_fragment_container, RoomFragment.newInstance())
                        .commit()
                setNavigationViewSignIn(it)
            }else{
                supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.layout_fragment_container, SignInFragment.newInstance(property!!.name))
                        .commit()
                setNavigationViewUnSignIn(property!!)
            }
        })
    }

    private fun setNavigationViewSignIn(user: User){
        nav_view.getHeaderView(0).property_name.text = user.name
        nav_view.getHeaderView(0).property_detail.text = user.email
        if(user.photo != ""){
            Glide.with(this).load(user.photo).into(nav_view.getHeaderView(0).property_logo)
        }
        nav_view.menu.setGroupVisible(R.id.signin_group,true)
        nav_view.menu.setGroupVisible(R.id.unsignin_group,false)
    }

    private fun setNavigationViewUnSignIn(property: Property){
        nav_view.getHeaderView(0).property_name.text = property.name
        nav_view.getHeaderView(0).property_detail.text = ""
        if(property.logo != ""){
            Glide.with(this).load(property.logo).into(nav_view.getHeaderView(0).property_logo)
        }else{
            Glide.with(this).load(R.mipmap.ic_launcher).into(nav_view.getHeaderView(0).property_logo)
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
        nav_view.menu.setGroupVisible(R.id.signin_group,false)
        nav_view.menu.setGroupVisible(R.id.unsignin_group,true)
    }

    override fun onResume() {
        super.onResume()
        if(PreferenceHelper(this).isUserRegister()){
            setNavigationViewSignIn(PreferenceHelper(this).getSessionUser())
        }else{
            setNavigationViewUnSignIn(property!!)
        }
    }

    override fun onBackPressed() {
        mDialog?.hideLoadingDialog()
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.menu_settings -> {
                startActivity(Intent(this,SettingActivity::class.java))
            }
            R.id.menu_signout -> {
                PreferenceHelper(this).resetSessionUser()
                ViewModelProviders.of(this).get(PinViewModel::class.java).resetUser()
                viewModel.setUser(User(""))
            }
            R.id.menu_exit -> {
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
