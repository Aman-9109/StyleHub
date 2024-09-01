package com.example.stylehub.repository

import com.example.stylehub.model.User
import com.example.stylehub.utils.State
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun addUserOnFireStore(user: User): Flow<State<String>>
}