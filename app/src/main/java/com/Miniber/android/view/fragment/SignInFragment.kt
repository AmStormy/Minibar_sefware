package com.Miniber.android.view.fragment

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Miniber.android.R
import com.Miniber.android.view.BaseFragment
import com.Miniber.android.view.activity.PinActivity
import kotlinx.android.synthetic.main.fragment_signin.view.*


/**
 * Created by chaiwut on 10/12/17.
 */

class SignInFragment : BaseFragment() {

    companion object {
        private val KEY_PROPERTY_NAME = "key_property_name"
        fun newInstance(key: String): Fragment {
            val fragment = SignInFragment()
            val bundle = Bundle()
            bundle.putString(KEY_PROPERTY_NAME, key)
            fragment.arguments = bundle
            return fragment
        }
    }

    lateinit var propertyTitle: String

    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        propertyTitle = arguments?.getString(KEY_PROPERTY_NAME).toString()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_signin, container, false)
        rootView.tv_property_name.text = propertyTitle
        rootView.btn_signin.setOnClickListener{
            startActivity(Intent(activity, PinActivity::class.java))
        }

        return rootView
    }
}