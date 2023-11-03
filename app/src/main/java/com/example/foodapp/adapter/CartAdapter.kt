package com.example.foodapp.adapter;

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.databinding.ItemListCartBinding


class CartAdapter(var listData: List<FoodEntity>, var onDeleteItemClick: ((FoodEntity) -> Unit)) :
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    var default: ((FoodEntity) -> Unit)? = null
    var plusCount: ((FoodEntity) -> Unit)? = null
    var minCount: ((FoodEntity) -> Unit)? = null

    class ViewHolder(var binding: ItemListCartBinding) : RecyclerView.ViewHolder(binding.root) {
        fun getData(itemData: FoodEntity) {
            binding.tvMenuPriceCart.text = itemData.price.toString()
            binding.tvMenuNameCart.text = itemData.name
            Glide.with(itemView).load(itemData.image).into(binding.ivMenuCartImg)
            binding.number.text = itemData.stock.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        var view = ItemListCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        holder.getData(listData[position])
        holder.binding.btnDecrement.setOnClickListener {
            val num = holder.binding.number.text.toString().toInt()
            if (num - 1 != 0) {
                minCount?.invoke(listData[position])
                holder.binding.number.text = "${num - 1}"
            }
        }
        holder.binding.btnIncrement.setOnClickListener {
            plusCount?.invoke(listData[position])
            holder.binding.number.text = "${1 + holder.binding.number.text.toString().toInt()}"
        }
        holder.binding.deleteCart.setOnClickListener {
            onDeleteItemClick(listData[position])
        }
    }

    fun setData(listData: List<FoodEntity>) {
        this.listData = listData
        notifyDataSetChanged()
    }

}