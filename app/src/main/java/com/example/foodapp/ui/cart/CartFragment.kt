package com.example.foodapp.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.R
import com.example.foodapp.adapter.CartAdapter
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CartFragment : Fragment() {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()
    private lateinit var cartAdapter: CartAdapter
    private var totalHarga = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCartBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageView2.setOnClickListener {
            findNavController().navigateUp()
        }
        setVM()
        cartViewModel.getFoodObserver().observe(viewLifecycleOwner) {
            cartAdapter.setData(it as ArrayList<FoodEntity>)
        }
        cartViewModel.getAllData()


        cartAdapter.default = { item ->
            val namaMenu = item.name
            val jumlah = item.stock
            val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
            val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
            val hargaBaru = hargaAwal * (item.stock)
            totalHarga += hargaBaru - totalHargaSementara
            binding.btnPesan.setOnClickListener {
                cartViewModel.postOrder(" ", totalHarga, namaMenu, jumlah)
            }

        }
        cartAdapter.plusCount = { item ->
            GlobalScope.launch {
                val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
                val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
                val hargaBaru = hargaAwal * (item.stock + 1)
                totalHarga += hargaBaru - totalHargaSementara
                cartViewModel.updateCount(item.stock + 1, item.id, hargaBaru)
                cartViewModel.getAllData()

                activity?.runOnUiThread {
                    val tvTotalPrice = view.findViewById<TextView>(R.id.tv_total_harga)
                    tvTotalPrice.text = "Rp. $totalHarga"
                }
            }
        }

        cartAdapter.minCount = { item ->
            GlobalScope.launch {
                val hargaAwal = item.price.replace("[^0-9]".toRegex(), "").toInt() / item.stock
                val totalHargaSementara = item.price.replace("[^0-9]".toRegex(), "").toInt()
                if (item.stock > 1) {
                    val hargaBaru = hargaAwal * (item.stock - 1)
                    totalHarga += hargaBaru - totalHargaSementara
                    cartViewModel.updateCount(item.stock - 1, item.id, hargaBaru)
                    cartViewModel.getAllData()

                    activity?.runOnUiThread {
                        val tvTotalPrice = view.findViewById<TextView>(R.id.tv_total_harga)
                        tvTotalPrice.text = "Rp. $totalHarga"
                    }
                }
            }
        }

    }


    private fun setVM() {
        cartAdapter = CartAdapter(ArrayList()) { foodEntity ->
            cartViewModel.deleteFood(foodEntity)
        }
        binding.rvCart.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvCart.adapter = cartAdapter
    }

    override fun onStart() {
        super.onStart()
    }

}