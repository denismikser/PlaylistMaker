package com.example.playlistmaker

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    val context = LocalContext.current
    val textColor = Color(0xFF1A1B22)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Верхняя панель с кнопкой "Назад" и заголовком
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                // Завершаем текущую активити (возврат назад)
                (context as? ComponentActivity)?.finish()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Назад",
                    tint = textColor
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(id = R.string.settings_title),
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Medium
            )
        }

        // Пункт "Тёмная тема" (пока просто заглушка)
        SettingsItem(
            title = stringResource(id = R.string.dark_theme),
            onClick = { /* Логика переключения темы будет позже */ }
        )

        // Пункт "Поделиться приложением"
        SettingsItem(
            title = stringResource(id = R.string.share_app),
            onClick = {
                val shareIntent = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT, context.getString(R.string.share_text))
                }
                context.startActivity(Intent.createChooser(shareIntent, null))
            }
        )

        // Пункт "Написать разработчикам"
        SettingsItem(
            title = stringResource(id = R.string.write_to_support),
            onClick = {
                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:") // Только почтовые клиенты
                    putExtra(Intent.EXTRA_EMAIL, arrayOf(context.getString(R.string.support_email)))
                    putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.support_email_subject))
                    putExtra(Intent.EXTRA_TEXT, context.getString(R.string.support_email_body))
                }
                context.startActivity(emailIntent)
            }
        )

        // Пункт "Пользовательское соглашение"
        SettingsItem(
            title = stringResource(id = R.string.user_agreement),
            onClick = {
                val browserIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(context.getString(R.string.offer_url))
                }
                context.startActivity(browserIntent)
            }
        )
    }
}

// Вспомогательный composable для отображения строки настроек
@Composable
fun SettingsItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color(0xFF1A1B22),
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        // Стрелочка справа (пока текстом, чтобы не подключать лишние библиотеки)
        Text(
            text = ">",
            color = Color.Gray,
            fontSize = 20.sp
        )
    }
}