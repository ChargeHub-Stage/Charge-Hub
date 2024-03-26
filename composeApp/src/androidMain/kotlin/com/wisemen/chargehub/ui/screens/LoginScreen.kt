package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.Log
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons
import com.wisemen.chargehub.ui.components.TextFields
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.screens.destinations.HomeScreenDestination
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.Spacing
import com.wisemen.chargehub.ui.theme.TextStyles
import org.koin.java.KoinJavaComponent.inject
import screens.login.LoginScreenUiAction
import screens.login.LoginScreenUiEvent
import screens.login.LoginScreenUiState
import screens.login.LoginScreenViewModel

@Preview
@Composable
fun LoginScreenPreview() {
    AppTheme {
        LoginLayout(state = LoginScreenUiState(), onAction = {})
    }
}

@Composable
@Destination
@ChargeHubNavGraph
fun LoginScreen(navController: NavController) {
    val viewModel: LoginScreenViewModel by inject(LoginScreenViewModel::class.java)

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginScreenUiEvent.ClickedBackButtonEvent -> navController.popBackStack()
                is LoginScreenUiEvent.ClickedLoginButtonEvent -> navController.navigate(
                    HomeScreenDestination
                )
                is LoginScreenUiEvent.ClickedForgotPasswordButtonEvent -> {}
            }
        }
    }
    val state = viewModel.state.collectAsState()
    LoginLayout(state.value, viewModel::onAction)
}

@androidx.annotation.OptIn(UnstableApi::class)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginLayout(
    state: LoginScreenUiState,
    onAction: (LoginScreenUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.login),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyles.topBarTitle,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(LoginScreenUiAction.OnClickedBackButtonAction) },
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.ChevronLeft,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Colors.white),
            )
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = Spacing.small, top = 70.dp),
                text = stringResource(R.string.welcome_back),
                style = TextStyles.title,
            )

            Text(
                stringResource(R.string.login_welcome_text),
                style = TextStyles.text,
            )

            Column {
                TextFields.EmailTextField(
                    modifier = Modifier.padding(horizontal = Spacing.medium).padding(bottom = 5.dp),
                    email = state.email,
                    isValid = true,
                    onInputChanged = { onAction(LoginScreenUiAction.OnEmailChangedAction(it)) },
                )

                TextFields.PasswordTextField(
                    modifier = Modifier.padding(horizontal = Spacing.medium),
                    onInputChanged = { onAction(LoginScreenUiAction.OnPasswordChangedAction(it)) },
                    onTogglePasswordVisibility = {
                        onAction(LoginScreenUiAction.OnClickedPasswordVisibilityButtonAction)
                    },
                    currentPassword = state.password,
                    isPasswordVisible = state.passwordVisibility,
                    isValid = true,
                )

                Text(
                    modifier = Modifier
                        .padding(bottom = 30.dp, start = Spacing.medium)
                        .clickable {
                            onAction(LoginScreenUiAction.OnClickedForgotPasswordButtonAction)
                        },
                    text = stringResource(R.string.forgot_password),
                    style = TextStyles.small_text,
                )
            }

            Buttons.PrimaryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = Spacing.medium),
                text = stringResource(R.string.login),
                colors = ButtonDefaults.primaryButtonColors(),
                onClick = { onAction(LoginScreenUiAction.OnClickedLoginButtonAction) },
            )
        }
    }
}