package com.example.personalapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.personalapp.Fragments.HomeFragment
import com.example.personalapp.Fragments.OrderFragment
import com.example.personalapp.Fragments.TrackFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.card_layout.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val orderFragment = OrderFragment()
        val trackFragment = TrackFragment()
        val btn = findViewById<Button>(R.id.btnOrder)

        makeCurrentFragment(homeFragment)
        btn.setOnClickListener(){
            val intent = Intent(this, FoodView::class.java)
            startActivity(intent)
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.login -> makeCurrentFragment(homeFragment)
                R.id.order -> {
                    val intent = Intent(this, FoodView::class.java)
                    startActivity(intent)
                }
                R.id.trackOrder -> makeCurrentFragment(trackFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fr_wrapper, fragment)
            commit()
        }



}