package com.example.mygithubuserapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuserapp.database.FavoriteUser
import com.example.mygithubuserapp.database.FavoriteUserRepository

class FavoritedUsersViewModel(application: Application) : ViewModel() {

    private val mFavoriteUserRepository: FavoriteUserRepository = FavoriteUserRepository(application)

    fun getAllFavoritedUsers(): LiveData<List<FavoriteUser>> = mFavoriteUserRepository.getAllFavoritedUsers()

    fun addFavoriteUser (favoriteUser: FavoriteUser?) = mFavoriteUserRepository.insert(favoriteUser as FavoriteUser)

    fun deleteFavoriteUser (favoriteUser: FavoriteUser?) = mFavoriteUserRepository.delete(favoriteUser as FavoriteUser)

    fun getFavoriteUserByUsername (username : String): LiveData<FavoriteUser> = mFavoriteUserRepository.getFavoriteUserByUsername(username)

}