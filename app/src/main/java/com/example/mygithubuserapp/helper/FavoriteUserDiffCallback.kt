package com.example.mygithubuserapp.helper

import androidx.recyclerview.widget.DiffUtil
import com.example.mygithubuserapp.database.FavoriteUser

class FavoriteUserDiffCallback(private val mOldFavoriteUsersList: List<FavoriteUser>, private val mNewFavoriteUsersList: List<FavoriteUser>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldFavoriteUsersList.size
    }
    override fun getNewListSize(): Int {
        return mNewFavoriteUsersList.size
    }
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldFavoriteUsersList[oldItemPosition].id == mNewFavoriteUsersList[newItemPosition].id
    }
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldFavoritedUser = mOldFavoriteUsersList[oldItemPosition]
        val newFavoritedUser = mNewFavoriteUsersList[newItemPosition]
        return oldFavoritedUser.id == newFavoritedUser.id && oldFavoritedUser.username == newFavoritedUser.username && oldFavoritedUser.avatarUrl == newFavoritedUser.avatarUrl
    }
}