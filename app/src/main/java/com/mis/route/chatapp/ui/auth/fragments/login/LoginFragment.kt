package com.mis.route.chatapp.ui.auth.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.MainActivity

class LoginFragment : BaseFragment<LoginViewModel,FragmentLoginBinding>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.vm = viewModel
        viewModel.events.observe(viewLifecycleOwner){
            when(it){
                is LoginViewEvent.NavigateToRegister -> {
                    navigateToRegister()
                }
                is LoginViewEvent.NavigateToHome ->{
                    navigateToHome()
                }

            }
        }
    }

    override fun getLayoutId(): Int = R.layout.fragment_login

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]
    
    
      
    
    private fun navigateToRegister(){
        if (activity == null) return
        (activity as MainActivity).navController
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun navigateToHome(){

    }
}
