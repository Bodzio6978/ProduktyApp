package com.gmail.bogumilmecel2.produkty.feature_splash.presentation

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.presentation.ui.theme.Error
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashViewModel = hiltViewModel()
) {

    val splashState = viewModel.splashState.value

    LaunchedEffect(key1 = true) {
        viewModel.getAccessToken()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (splashState is SplashState.Error) {
                Text(
                    text = splashState.message,
                    style = MaterialTheme.typography.body1.copy(
                        color = Error
                    )
                )

            } else {

                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h1
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = R.string.loading_items_please_wait),
                    style = MaterialTheme.typography.body1
                )

                Spacer(modifier = Modifier.height(12.dp))

                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp)
                )

            }

        }


    }


}