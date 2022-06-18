package com.gmail.bogumilmecel2.produkty.feature_items.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriceItem(
    name:String,
    value:String,
    currency:String
) {

    Column {
        Text(
            text = name,
            style = MaterialTheme.typography.body2.copy(
                color = Color.Gray
            )
        )

        Text(
            text = value+currency,
            style = MaterialTheme.typography.body2
        )
    }

}