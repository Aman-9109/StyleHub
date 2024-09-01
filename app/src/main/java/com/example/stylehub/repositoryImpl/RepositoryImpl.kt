package com.example.stylehub.repositoryImpl

import android.util.Log
import com.example.stylehub.model.User
import com.example.stylehub.repository.Repository
import com.example.stylehub.utils.State
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore
) : Repository {

    override fun addUserOnFireStore(user: User): Flow<State<String>> = callbackFlow {
        Log.d("TAG", "addUserOnFireStore: 1 ")

        trySend(State.Loading)
        auth.currentUser.let {
            val userId = auth.currentUser?.uid // Get the user ID inside the method
            if (userId != null) {
                Log.d("TAG", "addUserOnFireStore: 2 ")

                db.collection("users").document(userId).set(user)
                    .addOnSuccessListener {
                        Log.d("TAG", "addUserOnFireStore: $it ")
                        trySend(State.Success("User Added Successfully")).isSuccess
                        close() // Close the flow after sending success
                    }
                    .addOnFailureListener {
                        Log.d("TAG", "addUserOnFireStore: ${it.message} ")
                        trySend(State.Error("User Not Added")).isFailure
                        close() // Close the flow after sending error
                    }
            } else {
                Log.d("TAG", "addUserOnFireStore: 1 ")

                trySend(State.Error("User ID is null")).isFailure
                close() // Close the flow if user ID is null
            }

            awaitClose { /* No-op, already closed in listeners */ }
        }


    }
}
