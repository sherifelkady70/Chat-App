package com.mis.route.chatapp.ui.auth.fragments.register

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.database.User
import com.mis.route.chatapp.databinding.FragmentRegisterBinding

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_register
    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner, ::onEventChange)
    }

    private fun onEventChange(event: RegisterViewEvents) {
        when (event) {
            is RegisterViewEvents.NavigateToHome -> {
                navigateToHome(event.user)
            }
        }
    }

    private fun navigateToHome(user: User) {
        val action = RegisterFragmentDirections
            .actionRegisterFragmentToHomeFragment(user)
        findNavController()
            .navigate(action)
    }

    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}
