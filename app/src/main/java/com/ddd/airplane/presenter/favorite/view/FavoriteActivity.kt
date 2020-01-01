package com.ddd.airplane.presenter.favorite.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.databinding.FavoriteActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity

/**
 * @author jess
 */
class FavoriteActivity : BaseActivity<FavoriteActivityBinding>() {

    override fun setLayoutId() = R.layout.favorite_activity

    override fun initDataBinding() {
        dataBinding.run {
            lifecycleOwner = this@FavoriteActivity
        }
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
