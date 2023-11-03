package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemListGridBinding
import com.example.foodapp.data.remote.model.DataMenu

class GridAdapter (var listMenu: List<DataMenu>, var onItemClick: ((DataMenu) -> Unit)): RecyclerView.Adapter<com.example.foodapp.adapter.GridAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemListGridBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.foodapp.adapter.GridAdapter.ViewHolder {
        val view = ItemListGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: com.example.foodapp.adapter.GridAdapter.ViewHolder, position: Int) {
        holder.binding.tvNamaMakanan.text = listMenu[position].nama
        holder.binding.tvPriceFood.text = listMenu[position].harga.toString()
        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenu)
        holder.binding.cvItemCard.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }
}