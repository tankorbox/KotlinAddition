package com.example.honglinh.test.view.main

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.FragmentSearchBinding
import com.example.honglinh.test.view.adapter.SearchResultAdapter
import com.example.honglinh.test.viewmodel.main.search.SearchFragmentViewModel
import com.example.mvvmadditionkotlin.view.MVVMFragment

/**
 * Created by HoàngLinh on 12/6/2017.
 */
class SearchFragment : MVVMFragment<FragmentSearchBinding, SearchFragmentViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setBindingContentView(inflater, container, R.layout.fragment_search, BR.viewModel)
        setUpListSearchResults(binding!!.listSearchResult)
        return binding?.root
    }

    private fun setUpListSearchResults(listSearchResult: RecyclerView) {
        listSearchResult.layoutManager = LinearLayoutManager(context)
        listSearchResult.itemAnimator = DefaultItemAnimator()
        listSearchResult.adapter = SearchResultAdapter()
    }
}