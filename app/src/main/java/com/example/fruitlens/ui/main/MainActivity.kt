package com.example.fruitlens.ui.main

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.fruitlens.R
import com.example.fruitlens.ViewModelFactory
import com.example.fruitlens.databinding.ActivityMainBinding
import com.example.fruitlens.ui.login.LoginActivity
import com.example.fruitlens.utils.Injection
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>() {
        ViewModelFactory { Injection.provideUserRepository(this) }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val navView: BottomNavigationView = binding.navView

        val states = arrayOf(
            intArrayOf(android.R.attr.state_checked),
            intArrayOf(-android.R.attr.state_checked)
        )

        val colors = intArrayOf(
            ContextCompat.getColor(this, R.color.color_selected),
            ContextCompat.getColor(this, R.color.color_unselected)
        )

        val colorStateList = ColorStateList(states, colors)
        navView.itemIconTintList = colorStateList
        navView.itemIconTintList = colorStateList
        navView.itemTextColor = colorStateList

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_add_detection, R.id.navigation_history
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        viewModel.getSession().observe(this) { user ->
            user?.let {
                if (user.isLogin) {
//                    startActivity(Intent(this, LoginActivity::class.java))
                } else {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            } ?: run {
                startActivity(Intent(this, LoginActivity::class.java))
                Log.e("HomeFragment", "User session is null")
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            viewModel.logout()
        }
        return super.onOptionsItemSelected(item)
    }
}