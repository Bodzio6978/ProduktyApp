package com.gmail.bogumilmecel2.produkty.common.presentation.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomToolbar(
    title: String,
    isBackArrowVisible: Boolean,
    onBackArrowClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h1,
                color = Color.White
            )
        },
        contentColor = MaterialTheme.colors.background,
        elevation = 0.dp,
        navigationIcon = if (isBackArrowVisible) {
            {
                IconButton(onClick = { onBackArrowClicked() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        } else {
            null
        },
    )
}