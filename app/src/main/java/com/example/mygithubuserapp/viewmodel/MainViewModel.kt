package com.example.mygithubuserapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuserapp.api.ApiConfig
import com.example.mygithubuserapp.api.DetailUserResponse
import com.example.mygithubuserapp.api.GithubUsersResponse
import com.example.mygithubuserapp.api.ItemsItem
import com.example.mygithubuserapp.database.FavoriteUser
import com.example.mygithubuserapp.database.FavoriteUserRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _userItems = MutableLiveData<List<ItemsItem>>()
    val userItems: LiveData<List<ItemsItem>> = _userItems

    private val _userDetails = MutableLiveData<DetailUserResponse>()
    val userDetails: LiveData<DetailUserResponse> = _userDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoadingDetail = MutableLiveData<Boolean>()
    val isLoadingDetail: LiveData<Boolean> = _isLoadingDetail

    private val _isLoadingFollowersFollowing = MutableLiveData<Boolean>()
    val isLoadingFollowersFollowing: LiveData<Boolean> = _isLoadingFollowersFollowing

    private val _usersFollowers = MutableLiveData<List<ItemsItem>>()
    val usersFollowers: LiveData<List<ItemsItem>> = _usersFollowers

    private val _usersFollowing = MutableLiveData<List<ItemsItem>>()
    val usersFollowing: LiveData<List<ItemsItem>> = _usersFollowing

    init {
        findUser("Arif")
    }

    fun findUser(param: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().getUsers(param)
        client.enqueue(object : Callback<GithubUsersResponse> {
            override fun onResponse(
                call: Call<GithubUsersResponse>,
                response: Response<GithubUsersResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userItems.value = response.body()?.items
                } else {
                    Log.d(TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<GithubUsersResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getDetailUser(param: String) {
        _isLoadingDetail.value = true
        val client = ApiConfig.getApiService().getDetailUser(param)
        client.enqueue(object : Callback<DetailUserResponse> {

            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoadingDetail.value = false
                if (response.isSuccessful) {
                    _userDetails.value = response.body()
                } else {
                    Log.d(TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoadingDetail.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowers(param: String) {
        _isLoadingFollowersFollowing.value = true
        val client = ApiConfig.getApiService().getFollowers(param)
        client.enqueue(object : Callback<List<ItemsItem>> {

            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingFollowersFollowing.value = false
                if (response.isSuccessful) {
                    _usersFollowers.value = response.body()
                } else {
                    Log.d(TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingFollowersFollowing.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getFollowing(param: String) {
        _isLoadingFollowersFollowing.value = true
        val client = ApiConfig.getApiService().getFollowing(param)
        client.enqueue(object : Callback<List<ItemsItem>> {

            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoadingFollowersFollowing.value = false
                if (response.isSuccessful) {
                    _usersFollowing.value = response.body()
                } else {
                    Log.d(TAG, "onFailure: $response")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoadingFollowersFollowing.value = false
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}
