package com.example.cfttest.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cfttest.R
import com.example.cfttest.model.Model
import kotlinx.android.synthetic.main.item_lauout.view.*

class CustomAdapter(val listModel: List<Model>):RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lauout, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = listModel.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listModel[position]
        holder.itemView.rec_text.text = item.number
    }
}