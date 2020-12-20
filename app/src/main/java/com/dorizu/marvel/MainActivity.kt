package com.dorizu.marvel

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dorizu.marvel.favorit.FavoritFragment
import com.dorizu.marvel.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation_bottom.setOnNavigationItemSelectedListener(this)

        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_home, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when(item.itemId){
            R.id.home_nav -> {
                fragment = HomeFragment()
                title = "Home"
            }
            R.id.favorit_nav -> {
                fragment = FavoritFragment()
                title = "Favorit"
            }
        }
        if (fragment != null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_home, fragment)
                .commit()
        }
        supportActionBar?.title = title

        return true
    }
}