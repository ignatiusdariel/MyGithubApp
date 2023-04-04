package com.example.mygithubuserapp.ui.main

import ViewModelFactory
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.mygithubuserapp.R
import com.example.mygithubuserapp.adapter.SectionsPagerAdapter
import com.example.mygithubuserapp.database.FavoriteUser
import com.example.mygithubuserapp.databinding.ActivityDetailUsersBinding
import com.example.mygithubuserapp.viewmodel.FavoritedUsersViewModel
import com.example.mygithubuserapp.viewmodel.MainViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.math.log

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUsersBinding
    private val detailViewModel by viewModels<MainViewModel>()

    private val favoritedUsersViewModel by viewModels<FavoritedUsersViewModel>() {
        ViewModelFactory.getInstance(this.application)
    }

    private var favoriteUser: FavoriteUser? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sectionsPagerAdapter = SectionsPagerAdapter(this)
        val username = intent.getStringExtra(EXTRA_OBJECT)
        sectionsPagerAdapter.username = username.toString()

        binding = ActivityDetailUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailViewModel.getDetailUser(username ?: "")

        detailViewModel.userDetails.observe(this) {
            binding.apply {
                detailFollowers.text = it.followers.toString() + " Followers"
                detailFollowing.text = it.following.toString() + " Following"
                detailUserLogin.text = it.login
                detailUserName.text = it.name
            }
            Glide.with(binding.root.context)
                .load(it.avatarUrl)
                .into(binding.detailAvatar)
            favoriteUser = FavoriteUser()
            favoriteUser?.username = it.login
            favoriteUser?.avatarUrl = it.avatarUrl
        }
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        detailViewModel.isLoadingDetail.observe(this) {
            showLoading(it)
        }

        supportActionBar?.elevation = 0f


        favoritedUsersViewModel.getFavoriteUserByUsername(username.toString()).observe(this) {
            binding.fabAddFav.setOnClickListener {
                favoritedUsersViewModel.deleteFavoriteUser(favoriteUser as FavoriteUser)
            }
//            if (it == null) {
//                binding.fabAddFav.setImageResource(R.drawable.ic_favorite_border_white)
//                binding.fabAddFav.setOnClickListener {
//                    favoritedUsersViewModel.addFavoriteUser(favoriteUser as FavoriteUser)
//                }
//            } else {
//                binding.fabAddFav.setImageResource(R.drawable.ic_favorite_white)
//                binding.fabAddFav.setOnClickListener {
//                    favoritedUsersViewModel.deleteFavoriteUser(favoriteUser as FavoriteUser)
//                }
//            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val EXTRA_OBJECT = "extra_object"

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_followers,
            R.string.tab_text_following
        )
    }
}
