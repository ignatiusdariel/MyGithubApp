package com.example.mygithubuserapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserapp.adapter.UsersAdapter
import com.example.mygithubuserapp.api.ItemsItem
import com.example.mygithubuserapp.databinding.FragmentFollowersFollowingBinding
import com.example.mygithubuserapp.viewmodel.MainViewModel

class FollowersFollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowersFollowingBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowersFollowingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        binding.rvFollowersFollowing.layoutManager = LinearLayoutManager(requireActivity())

        val position = arguments?.getInt(ARG_POSITION)
        val username = arguments?.getString(ARG_USERNAME)

        mainViewModel.getFollowers(username?:"")
        mainViewModel.getFollowing(username?:"")
        if (position == 1) {
            mainViewModel.usersFollowers.observe(viewLifecycleOwner) { Follower ->
                setUsersFollowersFollowing(Follower)
            }
        }
        if (position == 2) {
            mainViewModel.usersFollowing.observe(viewLifecycleOwner) { Following ->
                setUsersFollowersFollowing(Following)
            }
        }

        mainViewModel.isLoadingFollowersFollowing.observe(viewLifecycleOwner) {
            showLoading(it)
        }
    }

    private fun setUsersFollowersFollowing(items: List<ItemsItem>) {
        val usersAdapter = UsersAdapter(items)
        binding.rvFollowersFollowing.adapter = usersAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        const val ARG_POSITION = "Position"
        const val ARG_USERNAME = "Username"
    }
}
