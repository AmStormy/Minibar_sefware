package com.Miniber.android.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.Miniber.android.R
import com.Miniber.android.activity.PinActivity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.Miniber.android.activity.SplashViewModel


/**
 * Created by chaiwut on 2/12/17.
 */
class SplashFragment : _BaseFragment() {

    companion object {
        private val TAG = "SplashFragment"
    }

    private var viewModel: SplashViewModel? = null

////    private var mBinding: FragmentSplashBinding? = null
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        // Inflate this data binding layout
////        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)
//
//        // Create and set the adapter for the RecyclerView.
////        mCommentAdapter = CommentAdapter(mCommentClickCallback)
////        mBinding.commentList.setAdapter(mCommentAdapter)
//        return inflater.inflate(inflater, R.layout.fragment_splash, container, false)
//    }
//

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        subScriptions(viewModel!!)
    }

    private fun subScriptions(splashViewModel: SplashViewModel){

        splashViewModel.getLoading().observe(this, Observer<Boolean> { loadingState ->
            if(loadingState!!){
                Log.d(TAG,"loadingState = "+loadingState)
                val intent = Intent(context, PinActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }
        })
    }

}
