package com.example.mygithubuserapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mygithubuserapp.ui.main.DetailUserActivity
import com.example.mygithubuserapp.api.ItemsItem
import com.example.mygithubuserapp.databinding.ItemUserBinding

class UsersAdapter(private val listUser: List<ItemsItem>) :
    RecyclerView.Adapter<UsersAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ListViewHolder, position: Int) {
        viewHolder.binding.tvUserName.text = listUser[position].login
        Glide.with(viewHolder.itemView.context)
            .load(listUser[position].avatarUrl)
            .into(viewHolder.binding.imgUserPhoto)
        viewHolder.itemView.setOnClickListener {
            val detailActivityUser = Intent(viewHolder.itemView.context, DetailUserActivity::class.java)
            detailActivityUser.putExtra(DetailUserActivity.EXTRA_OBJECT, listUser[position].login)
            viewHolder.itemView.context.startActivity(detailActivityUser)
        }
    }

    override fun getItemCount() = listUser.size

    class ListViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)
}

