package com.example.honglinh.test.view

import android.os.Bundle
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.PeopleActivityBinding
import com.example.honglinh.test.viewmodel.PeopleViewModel
import com.example.mvvmadditionkotlin.view.MVVMActivity

/**
 * Created by HoàngLinh on 11/22/2017.
 */
class PeopleActivity : MVVMActivity<PeopleActivityBinding, PeopleViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setBindingContentView(R.layout.people_activity, BR.peoplevm)
    }
}
