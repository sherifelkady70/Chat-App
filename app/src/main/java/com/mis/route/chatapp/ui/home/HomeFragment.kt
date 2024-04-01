package com.mis.route.chatapp.ui.home

import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.ActivityHomeBinding

class HomeFragment : BaseFragment<HomeViewModel,ActivityHomeBinding>() {
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }
}