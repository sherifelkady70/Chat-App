package com.mis.route.chatapp.ui.auth.fragments.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.database.User
import com.mis.route.chatapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<RegisterViewModel,FragmentRegisterBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun getLayoutId(): Int = R.layout.fragment_register
    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

}
