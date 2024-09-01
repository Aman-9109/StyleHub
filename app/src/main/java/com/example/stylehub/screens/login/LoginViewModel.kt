package com.example.stylehub.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stylehub.repositoryImpl.RepositoryImpl
import com.example.stylehub.utils.State
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repositoryImpl: RepositoryImpl) : ViewModel() {

    private var loginStatus = MutableStateFlow<State<Any>>(State.Idle)
    var _loginStatus = loginStatus.asStateFlow()



    fun loginWithFireBase(email: String, password: String) = viewModelScope.launch {
        try {
            loginStatus.value = State.Loading
            val fb = Firebase.auth
            fb.signInWithEmailAndPassword(email, password).addOnSuccessListener {


            }.addOnFailureListener {
                Log.d("TAG", "loginWithFireBase:${it.message} ")

            }
            loginStatus.value = State.Success("Success")


        } catch (e: Exception) {
            loginStatus.value = State.Error(e.message.toString())

        }

    }


}