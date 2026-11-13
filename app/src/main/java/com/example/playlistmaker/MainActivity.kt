package com.example.playlistmaker

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController

// Этот класс запускает всё приложение
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    val navController = rememberNavController()
                    PlaylistHost(navController = navController)
                }
            }
        }
    }
}

// Ваш главный экран
@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val blueColor = Color(0xFF3772E7)
    val textColor = Color(0xFF1A1B22)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Синяя шапка
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

        // Список кнопок
        Column(modifier = Modifier.padding(top = 16.dp)) {
            MenuRow(
                title = "Поиск",
                iconRes = android.R.drawable.ic_menu_search,
                textColor = textColor,
                onClick = { onSearchClick() } // <-- Исправлено
            )
            MenuRow(
                title = "Плейлисты",
                iconRes = android.R.drawable.ic_menu_gallery,
                textColor = textColor,
                onClick = { /* Заглушка */ }
            )
            MenuRow(
                title = "Избранное",
                iconRes = android.R.drawable.star_on,
                textColor = textColor,
                onClick = { /* Заглушка */ }
            )
            MenuRow(
                title = "Настройки",
                iconRes = android.R.drawable.ic_menu_manage,
                textColor = textColor,
                onClick = { onSettingsClick() } // <-- Исправлено
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