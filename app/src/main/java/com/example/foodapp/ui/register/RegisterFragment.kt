package com.example.foodapp.ui.register

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.foodapp.R
import com.example.foodapp.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding:FragmentRegisterBinding
    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            val email = binding.etEmailRegister.text.toString()
            val password = binding.etPasswordRegister.text.toString()

            //memvalidasi jika email kosong
            if (email.isEmpty()){
                binding.etEmailRegister.error = "emailRequired"
                binding.etEmailRegister.requestFocus()
                return@setOnClickListener
            }
            //memvalidasi jika email tidak sesuai
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.etEmailRegister.error = "invalid Email"
                binding.etEmailRegister.requestFocus()
                return@setOnClickListener
            }
            //memvalidasi password
            if (password.isEmpty()){
                binding.etPasswordRegister.error = "password required"
                binding.etPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            //memvalidasi panjang password
            if (password.length < 6){
                binding.etPasswordRegister.error = "minimum register"
                binding.etPasswordRegister.requestFocus()
                return@setOnClickListener
            }
            if (!binding.outlinedTextField.isErrorEnabled && !binding.outlinedTextField2.isErrorEnabled) {
                viewModel.registerFirebase(email, password)
            }
        }
        viewModel.register.observe(viewLifecycleOwner) {
            if (it.equals("Register Success!", true)) {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        }

        binding.sudahPunyaAKun.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

    }

}