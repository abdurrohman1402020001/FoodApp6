package com.example.foodapp.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {

    private val _user = MutableLiveData<FirebaseUser?>()
    val user: LiveData<FirebaseUser?> = _user

    private val _update = MutableLiveData<String?>()
    val update: LiveData<String?> = _update

    fun session() {
        if (firebaseAuth.currentUser != null) {
            _user.postValue(firebaseAuth.currentUser)
        } else {
            _user.postValue(null)
        }
    }

    fun updateEmail(email: String) =
        firebaseAuth.currentUser?.updateEmail(email)?.addOnCompleteListener {
            if (it.isSuccessful) {
                _update.postValue("Update Success")
            } else {
                _update.postValue(it.exception.toString())
            }
        }

}