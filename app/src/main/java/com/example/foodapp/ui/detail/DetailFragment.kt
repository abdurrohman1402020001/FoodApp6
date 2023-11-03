package com.example.foodapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.data.local.database.entity.FoodEntity
import com.example.foodapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val ambilDataMenu = bundle!!.getString("namaMenu")
        val ambilDataharga = bundle.getString("hargaMenu")
        val ambilDataimeage = bundle.getString("gambar")
        val ambilDataketerangan = bundle.getString("description")
        val ambilDatakelokasi = bundle.getString("loc")
        binding.tvFoodName.text = ambilDataMenu
        binding.tvHargaDetail.text = ambilDataharga.toString()
        Glide.with(requireContext()).load(ambilDataimeage).into(binding.banner)
        Glide.with(requireContext()).load(ambilDataimeage).into(binding.ivProvImg)
        binding.tvLocation.text = ambilDatakelokasi
        binding.keterangan.text = ambilDataketerangan

        Glide.with(requireContext()).load(ambilDataimeage).into(binding.banner)
        binding.ivMaps.setOnClickListener {
            val data = binding.tvLocation.text.toString()
            val uri = Uri.parse("https://www.google.com/maps/search/$data")
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(mapIntent)
        }
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.btnAddCart.setOnClickListener {
            val stock = binding.number.text.toString()
            detailViewModel.insertData(
                FoodEntity(
                    0,
                    ambilDataMenu!!,
                    ambilDataimeage!!,
                    (ambilDataharga!!.replace("[^0-9]".toRegex(), "")
                        .toInt() * binding.number.text.toString().toInt()).toString(),
                    stock.toInt()
                )
            )
            findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }

        countingFood()
    }

    fun countingFood() {
        var foodTotal = 0
        binding.btnIncrement.setOnClickListener {
            foodTotal += 1
            binding.number.text = foodTotal.toString()
        }
        binding.btnDecrement.setOnClickListener {
            if (foodTotal > 0) {
                foodTotal -= 1
                binding.number.text = foodTotal.toString()
            }
        }
    }
}