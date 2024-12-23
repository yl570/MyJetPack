package com.yilei.jetpack


import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.yilei.jetpack.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.itemIconTintList=null
        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            //changeIcon(item.itemId)
            return@setOnItemSelectedListener true
        }
        val badge = binding.bottomNavigation.getOrCreateBadge(R.id.navigation_search)
        badge.maxNumber = 99
        // 设置数字
        badge.number = 100

        // 可以设置 Badge 的其他属性，比如背景颜色
        badge.backgroundColor = Color.RED // 设置背景颜色
        badge.isVisible = true // 显示 Badge
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
    }
    private fun changeIcon(id:Int){
        val ids= intArrayOf(R.id.navigation_home,R.id.navigation_search,R.id.navigation_profile)
        for (i in ids){
            if (i!=id){
                setUnselectedIcon(id)
            }else{
                setSelectedIcon(id)
            }
        }
    }
    private fun setUnselectedIcon(id: Int){
        when(id){
            R.id.navigation_home -> {
                binding.bottomNavigation.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home)
            }
            R.id.navigation_search -> {
                binding.bottomNavigation.menu.findItem(R.id.navigation_search).setIcon(R.drawable.ic_search)
            }
            R.id.navigation_profile -> {
                binding.bottomNavigation.menu.findItem(R.id.navigation_profile).setIcon(R.drawable.ic_profile)
            }
        }
    }
    private fun setSelectedIcon(id: Int){
        when(id){
            R.id.navigation_home -> {
                Log.d("TAG", "setSelectedIcon: navigation_home")
                binding.bottomNavigation.menu.findItem(R.id.navigation_home).setIcon(R.drawable.ic_home_selected)
            }
            R.id.navigation_search -> {
                binding.bottomNavigation.menu.findItem(R.id.navigation_search).setIcon(R.drawable.ic_search_selected)
            }
            R.id.navigation_profile -> {
                binding.bottomNavigation.menu.findItem(R.id.navigation_profile).setIcon(R.drawable.ic_profile_selected)
            }
        }
    }
}