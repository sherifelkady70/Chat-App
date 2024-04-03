package com.mis.route.chatapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mis.route.chatapp.R
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.databinding.ItemRoomBinding

class RoomsAdapter(var roomsList : List<Room>) : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemRoomBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_room,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return roomsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(roomsList[position])
    }

    fun updateList(newRoomsList: List<Room>) {
        roomsList=newRoomsList
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(room:Room){
            binding.room = room
            binding.executePendingBindings()
        }
    }
}