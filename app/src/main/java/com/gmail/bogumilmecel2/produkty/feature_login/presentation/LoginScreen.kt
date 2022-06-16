package com.gmail.bogumilmecel2.produkty.feature_login.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.gmail.bogumilmecel2.produkty.R
import com.gmail.bogumilmecel2.produkty.common.presentation.components.CustomToolbar

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val emailState = viewModel.emailState.value
    val passwordState = viewModel.passwordState.value

    Scaffold(
        topBar = {
            CustomToolbar(
                title = "Log into your account!",
                isBackArrowVisible = false
            )
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {


            Column(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {

                TextField(
                    value = emailState.value,
                    onValueChange = { newValue ->
                        viewModel.onEvent(LoginEvent.EnteredLogin(newValue))
                    },
                    placeholder = {
                        Text(text = emailState.hint)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(50))
                        .testTag(stringResource(id = R.string.email)),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.AccountCircle, contentDescription = emailState.hint)
                    }
                )
                
                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    value = passwordState.value,
                    onValueChange = { newValue ->
                        viewModel.onEvent(LoginEvent.EnteredPassword(newValue))
                    },
                    placeholder = {
                        Text(text = passwordState.hint)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(50))
                        .testTag(stringResource(id = R.string.password)),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = passwordState.hint)
                    }
                )
                
                Button(
                    onClick = {
                        viewModel.onEvent(LoginEvent.ClickedSignIn)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 40.dp)
                        .clip(RoundedCornerShape(50))
                ) {
                    Text(
                        text = stringResource(id = R.string.sign_in),
                        style = MaterialTheme.typography.button,
                        modifier = Modifier
                            .padding(vertical = 5.dp)
                    )
                }
            }
        }
    }
}