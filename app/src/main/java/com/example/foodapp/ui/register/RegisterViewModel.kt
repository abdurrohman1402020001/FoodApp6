package com.example.foodapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth): ViewModel() {
    private val _register = MutableLiveData<String>()
    val register: LiveData<String> = _register

    fun registerFirebase(email: String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _register.postValue("Register Success!")
            } else {
                _register.postValue(it.exception.toString())
            }
        }


}