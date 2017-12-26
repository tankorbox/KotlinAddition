package com.example.honglinh.test.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import com.example.honglinh.test.AppConfig
import com.example.honglinh.test.BR
import com.example.honglinh.test.R
import com.example.honglinh.test.databinding.ActivityMainBinding
import com.example.honglinh.test.view.main.*
import com.example.honglinh.test.viewmodel.main.MainViewModel
import com.example.mvvmadditionkotlin.view.MVVMActivity

class MainActivity : MVVMActivity<ActivityMainBinding, MainViewModel>() {

    companion object {
        val FRAGMENT_GROUP: HashMap<Int, Int> = hashMapOf(
                R.id.bottom_navigation_home to 1,
                R.id.bottom_navigation_repository to 2,
                R.id.bottom_navigation_events to 3,
                R.id.bottom_navigation_profile to 4,
                R.id.searchView to 0
        )
    }


    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var searchEditText: AppCompatEditText
    private var oldFragment = FRAGMENT_GROUP.getValue(R.id.searchView)

    override fun onCreate(savedInstanceState: Bundle?) {
        AppConfig.appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setBindingContentView(R.layout.activity_main, BR.viewModel)

        setToolbar(R.id.tool_bar, false)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        setUpBottomNavigation()
        setUpSearchEditText()
        switchFragments(UserFragment(), FRAGMENT_GROUP.getValue(R.id.bottom_navigation_home))
    }

    /**
     * config bottom navagation
     */
    private fun setUpBottomNavigation() {
        bottomNavigationView = viewDataBinding.navigationBottom
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottom_navigation_home -> {
                    switchFragments(UserFragment(), FRAGMENT_GROUP.getValue(item.itemId))
                }
                R.id.bottom_navigation_repository -> {
                    switchFragments(RepositoryFragment(), FRAGMENT_GROUP.getValue(item.itemId))
                }
                R.id.bottom_navigation_events -> {
                    switchFragments(EventFragment(), FRAGMENT_GROUP.getValue(item.itemId))
                }
                R.id.bottom_navigation_profile -> {
                    switchFragments(ProfileFragment(), FRAGMENT_GROUP.getValue(item.itemId))
                }
                else -> {
                    false
                }
            }
        }
    }

    /**
     * config edittext and focus listener
     */
    private fun setUpSearchEditText() {
        searchEditText = viewDataBinding.searchView
        searchEditText.setOnFocusChangeListener { _, b -> if (b) switchFragments(SearchFragment(), FRAGMENT_GROUP.getValue(R.id.searchView)) }
        searchEditText.setOnClickListener { _ -> switchFragments(SearchFragment(), FRAGMENT_GROUP.getValue(R.id.searchView)) }
    }

    /**
     * switch fragment
     */
    private fun switchFragments(fragment: Fragment, targetFragment: Int): Boolean {
        if (targetFragment == oldFragment) return true

        val fm = supportFragmentManager
        val ft = fm.beginTransaction()

        if (targetFragment == 0 || oldFragment == 0) {

        } else {
            val direction = targetFragment - oldFragment
            when {
                direction > 0 -> {
                    ft.setCustomAnimations(R.anim.enter_right_to_left, R.anim.exit_right_to_left)
                }
                direction < 0 -> {
                    ft.setCustomAnimations(R.anim.enter_left_to_right, R.anim.exit_left_to_right)
                }
            }
        }

        ft.replace(R.id.frContainer, fragment)
        ft.commit()
        oldFragment = targetFragment
        return true
    }
}
