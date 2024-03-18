package com.wisemen.chargehub.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.theme.AppTheme
import org.koin.java.KoinJavaComponent.inject
import screens.login.LoginScreenUiAction
import screens.login.LoginScreenUiEvent
import screens.login.LoginScreenViewModel

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginLayout {}
    }
}

@Composable
@Destination
@ChargeHubNavGraph
fun LoginScreen(
    navController: NavController
) {
    val viewModel: LoginScreenViewModel by inject(LoginScreenViewModel::class.java)

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when(event) {
                is LoginScreenUiEvent.ClickedBackButtonEvent -> {}
                is LoginScreenUiEvent.ClickedLoginButtonEvent -> {}
                is LoginScreenUiEvent.ClickedForgotPasswordButtonEvent -> {}
            }
        }
    }
    LoginLayout(viewModel::onAction)
}

@Composable
fun LoginLayout(onAction: (LoginScreenUiAction) -> Unit) {
    Text(text = "test")
}