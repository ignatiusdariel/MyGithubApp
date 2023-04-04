package com.example.mygithubuserapp.database

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteUserRepository(application: Application) {
    private val mFavUserDao: FavoriteUserDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteUserRoomDatabase.getDatabase(application)
        mFavUserDao = db.favoriteUserDao()
    }

    fun insert(favoriteUser: FavoriteUser) = executorService.execute { mFavUserDao.insert(favoriteUser) }

    fun delete(favoriteUser: FavoriteUser) = executorService.execute { mFavUserDao.delete(favoriteUser) }

    fun getFavoriteUserByUsername(username: String): LiveData<FavoriteUser> = mFavUserDao.getFavoriteUserByUsername(username)

    fun getAllFavoritedUsers(): LiveData<List<FavoriteUser>> = mFavUserDao.getAllFavoritedUsers()
}