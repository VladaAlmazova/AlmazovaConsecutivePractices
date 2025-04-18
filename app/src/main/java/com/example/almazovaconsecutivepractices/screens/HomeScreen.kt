package com.example.almazovaconsecutivepractices.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize

/**
 * UI домашнего экрана
 */
@Composable
fun HomeContent(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Домашний экран") },
            )
        },
        content = { paddingValues ->
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                Text("Контент домашнего экрана")
            }
        }
    )
}

/**
 * Реализация Screen
 */
@Parcelize
class HomeScreen(override val screenKey: ScreenKey = generateScreenKey()) : Screen {
    @Composable
    override fun Content(modifier: Modifier) {
        HomeContent(modifier)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeContent() {
    HomeContent()
}
