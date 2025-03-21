package com.example.almazovaconsecutivepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.example.almazovaconsecutivepractices.screens.MainTabScreen
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinContext {
                val rootScreen: RootScreen<DefaultStackScreen> = rememberRootScreen {
                    DefaultStackScreen(StackNavModel(MainTabScreen()))
                }
                rootScreen.Content(modifier = Modifier.fillMaxSize())
            }
        }
    }
}