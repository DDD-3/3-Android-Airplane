package com.ddd.airplane.presenter.favorite.view

import android.os.Bundle
import com.ddd.airplane.R
import com.ddd.airplane.common.provider.ContextProvider
import com.ddd.airplane.databinding.FavoriteActivityBinding
import com.ddd.airplane.presenter.base.BaseActivity
import com.ddd.airplane.presenter.favorite.viewmodel.FavoriteViewModel

/**
 * @author jess
 */
class FavoriteActivity : BaseActivity<FavoriteActivityBinding>() {

    private var vm = FavoriteViewModel(ContextProvider(this))

    override fun getLayoutId() = R.layout.favorite_activity

    override fun initDataBinding() {
        super.initDataBinding()
        dataBinding.vm = this.vm
    }

    override fun initLayout() {

    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
