package com.example.honglinh.test.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentProfileBinding
import com.example.honglinh.test.viewmodel.main.profile.ProfileFragmentViewModel
import com.example.mvvmadditionkotlin.view.MVVMFragment

/**
 * Created by HoàngLinh on 12/7/2017.
 */
class ProfileFragment : MVVMFragment<FragmentProfileBinding, ProfileFragmentViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setBindingContentView(inflater, container, R.layout.fragment_profile, BR.viewModel)
        return binding?.root
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
    }
}