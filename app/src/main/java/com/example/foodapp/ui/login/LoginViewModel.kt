package com.example.foodapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) : ViewModel() {
    private val _login = MutableLiveData<String>()
    val login: LiveData<String> = _login

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

    fun loginFirebase(email: String, password: String) =
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                _login.postValue("Login Success!")
            } else {
                _login.postValue(it.exception.toString())
            }
        }

}