package com.example.mygithubuserapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import com.example.mygithubuserapp.R
import com.example.mygithubuserapp.database.FavoriteUser
import com.example.mygithubuserapp.databinding.ActivityFavoritedUsersBinding
import com.example.mygithubuserapp.viewmodel.MainViewModel

class FavoritedUsersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorited_users)
    }
}