package com.yilei.jetpack


import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.yilei.jetpack.databinding.ActivityMainBinding
import com.yilei.jetpack.fragment.FirstFragment
import com.yilei.jetpack.fragment.SecondFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val fragments= listOf(FirstFragment(),SecondFragment())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigation.itemIconTintList=null
        binding.bottomNavigation.setOnItemSelectedListener { item: MenuItem ->
            //changeIcon(item.itemId)
            var position=0
            when(item.itemId){
                R.id.navigation_home -> {
                    position=0
                }
                R.id.navigation_search -> {
                    position=1
                }
            }
            binding.viewPager.currentItem = position
            return@setOnItemSelectedListener true
        }
        val pagerAdapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragments.size
            }

            override fun createFragment(position: Int): Fragment {
                return fragments[position]
            }
        }
        binding.viewPager.setAdapter(pagerAdapter)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0 -> {
                        binding.bottomNavigation.selectedItemId=R.id.navigation_home
                    }
                    1 -> {
                        binding.bottomNavigation.selectedItemId=R.id.navigation_search
                    }
                }
            }
        })
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

}