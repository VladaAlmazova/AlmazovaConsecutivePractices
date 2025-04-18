package com.example.almazovaconsecutivepractices.listWithDetails.presentation.screens.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SliderRating(
    modifier: Modifier, rating: Float,
    onRatingChanged: (Float) -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Slider(
            value = rating,
            onValueChange = { newValue -> onRatingChanged(newValue) },
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = "Рейтинг: ${"%.1f".format(rating)}/10",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}