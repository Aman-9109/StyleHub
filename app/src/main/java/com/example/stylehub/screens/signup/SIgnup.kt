import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.stylehub.navigation.ScreenRoutes
import com.example.stylehub.screens.signup.SignupViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
//@Preview(showBackground = true)
fun SignupScreen(modifier: Modifier = Modifier, navController: NavController) {
    val vm: SignupViewModel = hiltViewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var phoneNumber by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = "Sign Up",
            style = TextStyle(
                fontSize = MaterialTheme.typography.displaySmall.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(75.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
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
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "phone icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Phone Number") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(15.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Password,
                    contentDescription = "password icon"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(text = "Password") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        Spacer(modifier = Modifier.height(35.dp))
        Button(
            onClick = {
                vm.firebaseSignup(
                    email = email.trim(),
                    password = password.trim(),
                    onSuccess = {
                        vm.addUserOnFireStore(email.trim(),phoneNumber.trim(),password.trim())
                        navController.navigate(ScreenRoutes.MainScreen)
                    },
                    onError = {
                        Log.d("fb", "SignupScreen: Error: $it")
                    }
                )
            }, modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Sign Up")
        }
        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Already have an account?  ", fontSize = 15.sp)
            Text(
                text = "Login",
                fontSize = 17.sp,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    color = Color.Red.copy(alpha = 0.8f)
                ),
                modifier = Modifier.clickable {
                    navController.navigate(ScreenRoutes.Login)

                }
            )
        }
    }
}
