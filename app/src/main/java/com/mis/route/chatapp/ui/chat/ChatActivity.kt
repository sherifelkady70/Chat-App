package com.mis.route.chatapp.ui.chat

import android.content.AbstractThreadedSyncAdapter
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES.TIRAMISU
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.database.RoomMessage
import com.mis.route.chatapp.databinding.ActivityChatBinding
import com.mis.route.chatapp.ui.chat.adapters.MessagesAdapter
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>() {

    lateinit var room : Room
     private lateinit var adapter : MessagesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.chatVM = viewModel
        dataBinding.sendRoomBtn.setOnClickListener { navigateToRoomCreation() }
        getIntentFrom()
        viewModel.listeningChanges()
        initRV()
    }

    override fun initViewModel(): ChatViewModel =
        ViewModelProvider(this)[ChatViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_chat

    private fun navigateToRoomCreation() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
        finish()
    }

    private fun getIntentFrom(){
        room = if(Build.VERSION.SDK_INT < TIRAMISU){
            intent.getSerializableExtra(Constants.ROOM_KEY) as Room
        }else {
            intent.getSerializableExtra(Constants.ROOM_KEY, Room::class.java)!!
        }
        viewModel.room = room
    }

    private fun initRV(){
        adapter = MessagesAdapter(listOf())
        dataBinding.messagesRv.adapter = adapter
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.messagesLiveData.observe(this){
            adapter.updateMessagesList(it)
        }
    }
}