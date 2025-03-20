package com.example.almazovaconsecutivepractices.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize

@Parcelize
class ProfileScreen(override val screenKey: ScreenKey = generateScreenKey()) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        Text(
            text = "Профиль пользователя",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }
}

