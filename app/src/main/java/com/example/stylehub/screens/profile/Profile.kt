package com.example.stylehub.screens.profile

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.stylehub.navigation.NavSubGraph
import com.example.stylehub.navigation.ScreenRoutes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
//@Preview(showBackground = true)
fun ProfileScreen(
    paddingValues: PaddingValues,
    navController: NavHostController = NavHostController(LocalContext.current),
    onLogout: () -> Unit,
    onProfileCLicked: () -> Unit
) {
    val vm: ProfileViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(
            title = { Text(text = "Profile ") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding())
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Restrict profile picture size
            AsyncImage(
                model = "https://www.profilebakery.com/wp-content/uploads/2023/04/AI-Profile-Picture-400x400.jpg",
                contentDescription = "",
                modifier = Modifier
                    .sizeIn(maxHeight = 120.dp, maxWidth = 120.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "John Doe",
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
            )

            ProfileTabs("Your Profile", onClick = onProfileCLicked)
            ProfileTabs("Payment Method")
            ProfileTabs("My Orders")
            ProfileTabs("Settings")
            ProfileTabs("Help Centre")
            ProfileTabs("Privacy Policy")
            ProfileTabs("Log Out",onClick = {
                vm.signOutUser()
                onLogout()})


        }
    }


}

@Composable
private fun ProfileTabs(text: String, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable { onClick()}
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black.copy(0.5f)
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 20.dp)
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(end = 15.dp)
        )
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}