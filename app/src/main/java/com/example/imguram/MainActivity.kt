package com.example.imguram

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imguram.databinding.ActivityMainBinding
import com.example.imguram.ui.story.StoriesRecyclerAdapter
import com.example.imguram.ui.story.StoryViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val storiesViewModel by viewModels<StoryViewModel> ()
    private val storiesAdapter = StoriesRecyclerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.storiesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context , RecyclerView.HORIZONTAL , false)
            adapter = storiesAdapter
        }

        setUpNav()
        storiesViewModel.fetchTags()
    }

    private fun setUpNav(){
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_hot, R.id.navigation_top
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onResume() {
        super.onResume()
        storiesViewModel.tags.observe(this){
            storiesAdapter.submitList(it)
        }
    }
}