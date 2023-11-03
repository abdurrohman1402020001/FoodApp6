package com.example.foodapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemListLinearBinding
import com.example.foodapp.data.remote.model.DataMenu

class ListAdapter(var listMenu: List<DataMenu>, var onItemClick: ((DataMenu) -> Unit)): RecyclerView.Adapter<com.example.foodapp.adapter.ListAdapter.ViewHolder> (){
    class ViewHolder(var binding: ItemListLinearBinding): RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.foodapp.adapter.ListAdapter.ViewHolder {
        val view = ItemListLinearBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: com.example.foodapp.adapter.ListAdapter.ViewHolder, position: Int) {
        holder.binding.tvMenuNameCart.text = listMenu[position].nama
        holder.binding.tvMenuPriceCart.text = listMenu[position].harga.toString()
        Glide.with(holder.itemView).load(listMenu[position].imageUrl).into(holder.binding.ivMenuCartImg)
        holder.binding.listMenu.setOnClickListener {
            onItemClick(listMenu[position])
        }
    }

    override fun getItemCount(): Int {
        return listMenu.size
    }


}