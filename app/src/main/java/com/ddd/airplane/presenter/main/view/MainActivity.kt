package com.ddd.airplane.presenter.main.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.ddd.airplane.R
import com.ddd.airplane.common.utils.tryCatch
import com.ddd.airplane.databinding.MainActivityBinding
import com.ddd.airplane.presenter.base.view.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.main_tab_bar.*

/**
 * @author jess
 */
class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.main_activity)
        initDataBinding()
        initLayout()
    }

    private fun initDataBinding() {

    }

    private fun initLayout() {
        bnv_main.run {
            tryCatch {
                fragmentManager = supportFragmentManager
                init()
            }
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {

        }
    }
}
