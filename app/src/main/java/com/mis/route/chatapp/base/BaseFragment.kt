package com.mis.route.chatapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment<VM:ViewModel,DB:ViewDataBinding> : Fragment() {

    lateinit var viewModel : VM
    lateinit var dataBinding : DB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        dataBinding = DataBindingUtil.inflate(layoutInflater,getLayoutId(),container,false)
        dataBinding.lifecycleOwner=this
        return dataBinding.root
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel() : VM

    fun showLoading(){

    }
    fun hideLoading(){

    }

}