package com.example.almazovaconsecutivepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.ListScreen
import com.example.almazovaconsecutivepractices.ui.theme.AlmazovaConsecutivePracticesTheme
import com.github.terrakok.modo.Modo.rememberRootScreen
import com.github.terrakok.modo.stack.DefaultStackScreen
import com.github.terrakok.modo.stack.StackNavModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            val rootScreen = rememberRootScreen {
                DefaultStackScreen(StackNavModel(ListScreen()))
            }
            rootScreen.Content(modifier = Modifier.fillMaxSize())
        }
    }
}