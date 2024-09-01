package com.example.stylehub.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.stylehub.R
import com.example.stylehub.utils.ProductCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, navController: NavHostController = NavHostController(
        LocalContext.current
    ), paddingValues: PaddingValues
) {
    val categories = listOf("Men", "Women", "Kids", "Shoes", "Watches", "Bags")
    val selected = remember {
        mutableIntStateOf(-1)
    }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(bottom = paddingValues.calculateBottomPadding())
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {


        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .padding(top = 10.dp, start = 15.dp, end = 15.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val search = remember {
                mutableStateOf("")
            }
            TopAppBar(title = { Text(text = "") }, navigationIcon = {
                AsyncImage(
                    model = "https://www.profilebakery.com/wp-content/uploads/2023/04/AI-Profile-Picture-400x400.jpg",
                    contentDescription = "", modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )
            },
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.notification),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Black
                            )


                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp), tint = Color.Red.copy(0.7f)
                            )


                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.bag),
                                contentDescription = "",
                                modifier = Modifier.size(30.dp),
                                tint = Color.Black
                            )


                        }

                    }
                })



            OutlinedTextField(
                value = search.value,
                onValueChange = { search.value = it },
                modifier = Modifier

                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(50.dp),
                placeholder = {
                    Text(
                        text = "Search", style = TextStyle(fontSize = 16.sp)
                    )
                },
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Mic,
                        contentDescription = ""
                    )
                },
                colors = TextFieldDefaults.colors(unfocusedContainerColor = Color.Gray.copy(alpha = 0.2f))
            )
            Text(
                text = " See All ",
                style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.ExtraBold),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
            )
            LazyRow(modifier = Modifier.fillMaxWidth()) {
                itemsIndexed(categories) { it, items ->
                    LazyRowItem(
                        text = items,
                        isSelected = selected.intValue == it,
                        onClick = { selected.intValue = it }
                    )
                }


            }

        }

        //Banner
        Column(
            modifier = Modifier
                .padding(top = 5.dp)
                .height(200.dp)
                .fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "banner",
                contentScale = ContentScale.FillBounds
            )

        }
        Text(
            text = "Popular Products",
            style = TextStyle(
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier
                .align(Alignment.Start)
                .padding(top = 10.dp, start = 10.dp, bottom = 0.dp)
        )

        LazyRow(modifier = Modifier.fillMaxWidth()) {
            items(10) {
                ProductCard()
            }
        }


    }
}

@Preview(showBackground = true)
@Composable

fun LazyRowItem(text: String = "Men", isSelected: Boolean = false, onClick: () -> Unit = {}) {
    Card(
        shape = RoundedCornerShape(30.dp),
        modifier = Modifier
            .width(100.dp)
            .padding(5.dp)
            .clickable {
                isSelected != isSelected
                onClick.invoke()
            },
        colors = CardDefaults.cardColors(containerColor = if (isSelected) Color.Red.copy(alpha = 0.7f) else Color.Unspecified)
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            fontSize = 15.sp,
            color = Color.Black,
            fontWeight = FontWeight.W400
        )

    }

}