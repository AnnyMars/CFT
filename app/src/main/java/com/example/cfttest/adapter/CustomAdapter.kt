package com.example.cfttest.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cfttest.R
import com.example.cfttest.model.AdapterModel
import kotlinx.android.synthetic.main.item_lauout.view.*

class CustomAdapter(private val items: MutableList<AdapterModel> ):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lauout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.rec_text.text = item.number
    }
}