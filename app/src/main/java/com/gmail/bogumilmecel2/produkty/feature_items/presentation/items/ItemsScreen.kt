package com.gmail.bogumilmecel2.produkty.feature_items.presentation.items

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ItemsScreen(
    viewModel: ItemsViewModel = hiltViewModel()
) {
    
    Text(text = "items screen")

}