package com.example.playlistmaker

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    val blueColor = Color(0xFF3772E7)
    val textColor = Color(0xFF1A1B22)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = blueColor,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Playlist maker",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Column(modifier = Modifier.padding(top = 16.dp)) {
            MenuRow(
                title = "Поиск",
                iconRes = android.R.drawable.ic_menu_search,
                textColor = textColor,
                onClick = {
                    val intent = Intent(context, SearchActivity::class.java)
                    context.startActivity(intent)
                }
            )
            MenuRow(
                title = "Плейлисты",
                iconRes = android.R.drawable.ic_menu_gallery,
                textColor = textColor,
                onClick = {

                }
            )
            MenuRow(
                title = "Избранное",
                iconRes = android.R.drawable.star_on,
                textColor = textColor,
                onClick = {

                }
            )
            MenuRow(
                title = "Настройки",
                iconRes = android.R.drawable.ic_menu_manage,
                textColor = textColor,
                onClick = {
                    val intent = Intent(context, SettingsActivity::class.java)
                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun MenuRow(
    title: String,
    iconRes: Int,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            tint = textColor,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = title,
            color = textColor,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )

        Text(
            text = ">",
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}