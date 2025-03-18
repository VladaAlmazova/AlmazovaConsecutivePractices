package com.example.almazovaconsecutivepractices

//import com.example.almazovaconsecutivepractices.screens.NavigationScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.ListScreen
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val rootScreen: RootScreen<DefaultStackScreen> = rememberRootScreen {
                DefaultStackScreen(StackNavModel(ListScreen()))
            }
            rootScreen.Content(modifier = Modifier.fillMaxSize())
        }
    }

//    @Composable
//    fun BottomNavigationBar() {
//        val items = listOf(
//            NavItem("Home", Icons.Filled.Home, HomeScreen()),
//            NavItem("Profile", Icons.Filled.Person, HomeScreen()),
//            NavItem("Anime list", Icons.Filled.List, ListScreen())
//        )
//
//        val stackNavigation = LocalStackNavigation.current
//
//        NavigationBar {
//            items.forEach { item ->
//                NavigationBarItem(
//                    icon = { Icon(item.icon, contentDescription = item.label) },
//                    label = { Text(item.label) },
//                    selected = stackNavigation.back()::class == item.screen::class,
//                    onClick = { stackNavigation.forward(item.screen) }
//                )
//            }
//        }
//    }
//
//    data class NavItem(val label: String, val icon: ImageVector, val screen: Screen)
}