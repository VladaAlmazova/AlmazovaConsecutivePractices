package com.example.almazovaconsecutivepractices.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.ListScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.stack.LocalStackNavigation
import com.github.terrakok.modo.stack.replace


@Composable
fun BottomNavigationBar() {
    val items = listOf(
        NavItem("Home", Icons.Filled.Home, HomeScreen()),
        NavItem("Profile", Icons.Filled.Person, ProfileScreen()),
        NavItem("Anime list", Icons.Filled.List, ListScreen())
    )

    val stackNavigation = LocalStackNavigation.current
    val currentScreen: Screen = LocalStackNavigation.current.navigationState.stack.last()

    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentScreen::class == item.screen::class,
                onClick = { stackNavigation.replace(item.screen) }
            )
        }
    }
}

data class NavItem(val label: String, val icon: ImageVector, val screen: Screen)

@Composable
fun CircularBackButton(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    Box(modifier = modifier.fillMaxSize()) {
        IconButton(
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colors.primary) // Фон кнопки
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Назад",
                tint = Color.White
            )
        }
    }
}