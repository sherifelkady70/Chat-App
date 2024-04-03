package com.mis.route.chatapp.ui.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityChatBinding
import com.mis.route.chatapp.ui.createroom.RoomCreationActivity

class ChatActivity : BaseActivity<ChatViewModel,ActivityChatBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.sendRoomBtn.setOnClickListener { navigateToRoomCreation() }
        getIntentFrom()
    }

    override fun initViewModel(): ChatViewModel =
        ViewModelProvider(this)[ChatViewModel::class.java]

    override fun getLayoutId(): Int = R.layout.activity_chat

    private fun navigateToRoomCreation() {
        startActivity(Intent(this, RoomCreationActivity::class.java))
        finish()
    }

    fun getIntentFrom(){

        val intent = Intent().getSerializableExtra(Constants.ROOM_KEY)
        Log.d("getIntentFrom","$intent")
    }

}