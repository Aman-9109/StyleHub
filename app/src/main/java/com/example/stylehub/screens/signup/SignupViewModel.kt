package com.example.stylehub.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylehub.model.User
import com.example.stylehub.repository.Repository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val repo: Repository,

    ) : ViewModel() {

    fun firebaseSignup(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = viewModelScope.launch {
        Firebase.auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
//                val user = User(name = "", email, phone, password)
//                repo.addUserOnFireStore(user)
                onSuccess()
            } else {
                onError(it.exception?.message.toString())
            }

        }


    }

    fun addUserOnFireStore(email: String, phone: String, password: String) =
        viewModelScope.launch {
            val user = User(name = "", email, phone, password)
            repo.addUserOnFireStore(user).collectLatest { }
        }
}