package com.mis.route.chatapp.ui.createroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityRoomCreationBinding

class RoomCreationActivity : AppCompatActivity() {

    lateinit var binding: ActivityRoomCreationBinding
    lateinit var viewModel: RoomCreationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_room_creation)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[RoomCreationViewModel::class.java]
        binding.vm = viewModel

        viewModel.events.observe(this) {
            when (it) {
                is RoomCreationEvents.RoomCreationEvent -> {
                    finish()
                }
            }
        }
    }
}