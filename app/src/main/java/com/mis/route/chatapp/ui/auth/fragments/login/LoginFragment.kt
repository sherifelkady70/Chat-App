package com.mis.route.chatapp.ui.auth.fragments.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.database.User
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.MainActivity

class LoginFragment : BaseFragment<LoginViewModel,FragmentLoginBinding>() {



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

    }

    private fun navigateToHome(){

    }
}
