package com.example.personalapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodRecyclerView: RecyclerView.Adapter<FoodRecyclerView.ViewHolder>() {

    
    private var foodName = arrayOf("Biryani","Burger","Ice Cream","Maggi","Noodles","Paanipuri Chaat","Pasta")
    private var foodPrice = arrayOf("Rs.100","Rs. 89","Rs. 30", "Rs. 40","Rs. 60","Rs. 25","Rs. 40")

    private val img = arrayOf(R.drawable.biryani,R.drawable.burger,R.drawable.icecream,R.drawable.maggi,R.drawable.noodles,
    R.drawable.panipurichaat,R.drawable.pasta)


    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

        lateinit var Itemimage: ImageView
        lateinit var itemt1: TextView
        lateinit var itemt2: TextView

        init {
            Itemimage = itemView.findViewById(R.id.relativeimage1)
            itemt1 = itemView.findViewById(R.id.text11)
            itemt2 = itemView.findViewById(R.id.text12)

            itemView.setOnClickListener{
                val position: Int = adapterPosition

                Toast.makeText(itemView.context, "you clicked on ", Toast.LENGTH_SHORT).show()
            }
        }

    }



    override fun getItemCount(): Int {
        return foodName.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.card_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if (holder != null) {
            holder.itemt1.text = foodName[position]
            holder.itemt2.text = foodPrice[position]
            holder.Itemimage.setImageResource(img[position])

        }

    }
}