package com.example.personalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_food_view.*

class FoodView : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<FoodRecyclerView.ViewHolder>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_view)

        layoutManager = LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
        adapter = FoodRecyclerView()
        recyclerView.adapter = adapter



    }
}