package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.remote.model.DataCategory
import com.example.foodapp.databinding.ItemMenuBinding

class CategoryAdapter(var listMenu: List<DataCategory>, var onItemClick: ((DataCategory) -> Unit)): RecyclerView.Adapter<CategoryAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemMenuBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvNamaMenu.text = listMenu[position].nama

        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenu)
        holder.binding.cvItemCard.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}