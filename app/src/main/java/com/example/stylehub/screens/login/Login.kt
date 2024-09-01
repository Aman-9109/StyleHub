package com.example.stylehub.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stylehub.navigation.ScreenRoutes
import com.example.stylehub.utils.State
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavController) {
    val vm: LoginViewModel = hiltViewModel()
    val loginState = vm._loginStatus.collectAsState()

    LaunchedEffect(key1 = Unit) {
        if (Firebase.auth.currentUser != null) {
            navController.navigate(ScreenRoutes.MainScreen)
        }

    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Login",
            style = TextStyle(
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(75.dp))


        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "person icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = "person icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = {
                vm.loginWithFireBase(email = username.trim(), password = password.trim())
                if (Firebase.auth.currentUser != null) {

                    navController.navigate(ScreenRoutes.MainScreen)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Sign In")
        }
        Spacer(modifier = Modifier.height(5.dp))

        when(loginState.value){
            is State.Error -> {

            }
            State.Loading -> {
                Box(modifier = Modifier.fillMaxSize().background(color = Color.White.copy(alpha = 0.2f)), contentAlignment = Alignment.Center) {

                    CircularProgressIndicator()
                }

            }
            is State.Success -> {

            }

            State.Idle -> {}
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have an account?  ", fontSize = 15.sp)
            Text(
                text = "Sign Up",
                fontSize = 17.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red.copy(alpha = 0.8f)
                ),
                modifier = Modifier.clickable {
                    navController?.navigate(ScreenRoutes.SignUp)

                }
            )


        }

    }

}