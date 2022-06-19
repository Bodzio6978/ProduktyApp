package com.gmail.bogumilmecel2.produkty.feature_items.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.feature_items.domain.model.Item

@Composable
fun ItemLayout(
    item: Item
) {
    val hasItemImage:Boolean? = item.image_link?.small?.isNotBlank()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clip(RoundedCornerShape(25))
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                hasItemImage?.let {
                    if (it){
                        Image(
                            painter = rememberAsyncImagePainter(item.image_link.small),
                            contentDescription = item.name,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                ) {

                    Row(
                        modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.body1
                            )

                            Text(
                                text = item.category.name,
                                style = MaterialTheme.typography.body2.copy(
                                    color = Color.Gray
                                )
                            )
                        }
                        Icon(
                            imageVector = Icons.Default.Circle,
                            contentDescription = "Circle",
                            tint = when(item.status){
                                "ENABLED" -> {
                                   Color.Green
                                }
                                "DISABLED" -> {
                                    Color.Yellow
                                }
                                else -> {
                                    Color.Red
                                }
                            },
                            modifier = Modifier
                                .padding(5.dp)
                                .size(10.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {

                        PriceItem(
                            name = stringResource(id = R.string.price),
                            value = item.price.amount.toString(),
                            currency = item.price.currency,
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        PriceItem(
                            name = stringResource(id = R.string.gross),
                            value = (item.price.amount - (item.price.amount * item.tax.rate / 100)).toString(),
                            currency = item.price.currency,
                        )

                        Spacer(modifier = Modifier.width(24.dp))

                        PriceItem(
                            name = stringResource(id = R.string.vat),
                            value = item.tax.name,
                            currency = "",
                        )
                    }

                }

            }
        }
    }
}