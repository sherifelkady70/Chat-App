package com.mis.route.chatapp.ui.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.mis.route.chatapp.R
import com.mis.route.chatapp.UserProvider
import com.mis.route.chatapp.database.RoomMessage
import com.mis.route.chatapp.databinding.RecevieMessageItemBinding
import com.mis.route.chatapp.databinding.SendMessagesItemBinding

class MessagesAdapter(var messages : List<RoomMessage>) : RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>() {

    companion object{
        const val SENDMESSAGES = 1
        const val RECEVIEMESSAGES = 0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesViewHolder {
        return if(viewType == SENDMESSAGES){
            val binding = DataBindingUtil.inflate<SendMessagesItemBinding>(LayoutInflater.from(parent.context),
                R.layout.send_messages_item,parent,false)
            MessagesViewHolder(binding)
        }else{
            val binding = DataBindingUtil.inflate<RecevieMessageItemBinding>(LayoutInflater.from(parent.context),
                R.layout.recevie_message_item,parent,false)
            MessagesViewHolder(binding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val myMessage = messages[position]
        return if(myMessage.senderId == UserProvider.user!!.uid){
            SENDMESSAGES
        }else{
            RECEVIEMESSAGES
        }
    }
    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: MessagesViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    fun updateMessagesList(newlist : List<RoomMessage>){
        messages = newlist
    }
    class MessagesViewHolder(var binding : ViewDataBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(message: RoomMessage){
            if(binding is SendMessagesItemBinding){
                (binding as SendMessagesItemBinding).roomMessage = message
            }
            if(binding is RecevieMessageItemBinding){
                (binding as RecevieMessageItemBinding).roomMessage = message
            }
        }
    }
}