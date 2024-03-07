package com.mis.route.chatapp.ui.auth.fragments.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.database.User
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.MainActivity

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }

    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginViewEvent.NavigateToRegister -> {
                    navigateToRegister()
                }
                is LoginViewEvent.NavigateToHome -> {
                    navigateToHome(event.user)
                }
            }
        }
    }

    private fun initViews() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun navigateToHome(user: User) {
        val action = LoginFragmentDirections
            .actionLoginFragmentToHomeFragment(user)
        findNavController().navigate(action)
    }

    private fun navigateToRegister() {
        if (activity == null) return
        (activity as MainActivity).navController
            .navigate(R.id.action_loginFragment_to_registerFragment)
    }
}
