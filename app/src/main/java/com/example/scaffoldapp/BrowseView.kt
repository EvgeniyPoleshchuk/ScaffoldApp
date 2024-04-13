package com.example.scaffoldapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun Browse() {
    val categories = listOf("Hits", "Happy", "Workout", "Running", "TGIF", "Yoga")
    LazyVerticalGrid(GridCells.Fixed(2)) {
        items(categories) { item ->
            BrowserItem(item, R.drawable.baseline_apps_24)
        }
    }

}