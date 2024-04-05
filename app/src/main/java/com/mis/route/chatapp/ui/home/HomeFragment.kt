package com.mis.route.chatapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.ActivityHomeBinding
import com.mis.route.chatapp.ui.chat.ChatActivity
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity

class HomeFragment : BaseFragment<HomeViewModel,ActivityHomeBinding>() {

    lateinit var adapter : RoomsAdapter
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
    }

    private fun initRv(){
        adapter = RoomsAdapter(listOf()){ room, position ->
            val intent = Intent(activity,ChatActivity::class.java)
            intent.putExtra(Constants.ROOM_KEY,room)
            intent.putExtra(Constants.ROOM_KEY_POSITION,position)
            startActivity(intent)
            Log.e("initRv","what happen")
        }
        viewModel.listOfRooms.observe(viewLifecycleOwner){
            adapter.updateList(it)
            Log.e("initRv","$it")
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