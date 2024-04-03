package com.mis.route.chatapp.ui.createroom

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.databinding.ActivityRoomCreationBinding

class RoomCreationActivity : BaseActivity<RoomCreationViewModel,ActivityRoomCreationBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.vm = viewModel
        custmizeSpinner()
    }


    private fun custmizeSpinner(){
        val array = arrayOf("sports","chat")
        val adapter : ArrayAdapter<String> = ArrayAdapter(this,R.layout.item_list,array)
        dataBinding.roomCategoryAtv.setAdapter(adapter)
    }
    override fun observeLiveData(){
        viewModel.events.observe(this) {
            when (it) {
                is RoomCreationEvents.RoomCreationEvent -> {
                    finish()
                }
            }
        }
    }
    override fun initViewModel(): RoomCreationViewModel {
        return ViewModelProvider(this)[RoomCreationViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_room_creation
    }


}