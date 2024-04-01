package com.mis.route.chatapp.ui.auth.fragments.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentRegisterBinding
import com.mis.route.chatapp.ui.auth.MainActivity

class RegisterFragment : BaseFragment<RegisterViewModel,FragmentRegisterBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.events.observe(viewLifecycleOwner){ event ->
            when(event) {
                is RegisterViewEvents.NavigateToHome -> {
                    navigateToHome()
                }
                is RegisterViewEvents.NavigateToLogin ->{
                    navigateToLogin()
                }
            }
        }
    }
    override fun getLayoutId(): Int = R.layout.fragment_register
    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    private fun navigateToHome(){
        if (activity == null) return
        (activity as MainActivity).navController
            .navigate(R.id.action_registerFragment_to_homeFragment)
    }

    private fun navigateToLogin(){
        if(activity == null) return
        (activity as MainActivity).navController
            .navigate(R.id.action_registerFragment_to_loginFragment)
    }
}
