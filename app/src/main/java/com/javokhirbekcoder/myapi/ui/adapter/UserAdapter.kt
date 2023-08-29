package com.javokhirbekcoder.myapi.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.javokhirbekcoder.myapi.databinding.UserItemBinding
import com.javokhirbekcoder.myapi.model.User


/*
Created by Javokhirbek on 20.08.2023 at 13:18
*/
class UserAdapter(val list: ArrayList<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: UserItemBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(user: User) {
            binding.userTv.text = user.username
            binding.passwordTv.text = user.password
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}