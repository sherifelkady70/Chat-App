package com.mis.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.ActivityHomeBinding
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity

class HomeFragment : BaseFragment<HomeViewModel,ActivityHomeBinding>() {

    private var adapter = RoomsAdapter(listOf())
    override fun getLayoutId(): Int {
        return R.layout.activity_home
    }

    override fun initViewModel(): HomeViewModel {
        return ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.addRoomBtn.setOnClickListener { navigateToCreateRoom() }
        initRv()
        viewModel.getList()
    }

    private fun initRv(){
        viewModel.listOfRooms.observe(viewLifecycleOwner){
            adapter.updateList(it)
        }
        dataBinding.recyclerView.adapter = adapter
    }
    override fun onResume() {
        super.onResume()
        viewModel.getList()
    }

    private fun navigateToCreateRoom(){
       startActivity(Intent(this@HomeFragment.requireContext(),RoomCreationActivity::class.java))
    }

}