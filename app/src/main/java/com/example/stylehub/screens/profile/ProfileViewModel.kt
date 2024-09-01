package com.example.stylehub.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ProfileViewModel @Inject constructor(val auth : FirebaseAuth) : ViewModel() {


    fun signOutUser() = viewModelScope.launch {
        auth.signOut()

    }

}
