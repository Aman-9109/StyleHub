package com.example.stylehub.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stylehub.R

@Composable
@Preview(showBackground = true)
fun ProductCard(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.padding(10.dp)
            .height(230.dp)
            .width(160.dp).clip(RoundedCornerShape(12.dp))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.tshirt),
                contentDescription = "",
                modifier = Modifier
                    .padding(10.dp)
                    .height(170.dp), contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Printed Cotton T-shirt",
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start = 5.dp).align(Alignment.Start)
            )
            Text(
                text = "$300",
                style = TextStyle(fontSize = 13.sp, fontWeight = FontWeight.SemiBold),
                modifier = Modifier.padding(start = 5.dp).align(Alignment.Start)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.heartoutlined),
            contentDescription = "",
            modifier = Modifier
                .padding(7.dp)
                .align(
                    Alignment.TopEnd
                )
                .size(27.dp)
        )


    }

}