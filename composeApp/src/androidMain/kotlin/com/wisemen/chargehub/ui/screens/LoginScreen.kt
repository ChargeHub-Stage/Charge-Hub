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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons
import com.wisemen.chargehub.ui.components.TextFields
import com.wisemen.chargehub.ui.components.primaryButtonColors
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
fun LoginScreen(
    navController: NavController
) {
    val viewModel: LoginScreenViewModel by inject(LoginScreenViewModel::class.java)

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LoginScreenUiEvent.ClickedBackButtonEvent -> navController.popBackStack()
                is LoginScreenUiEvent.ClickedLoginButtonEvent -> {}
                is LoginScreenUiEvent.ClickedForgotPasswordButtonEvent -> {}
            }
        }
    }
    LoginLayout(viewModel.state, viewModel::onAction)
}

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
                        text = stringResource(R.string.aanmelden),
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
    ) { padding ->
        Column(
            modifier = Modifier.padding(padding).fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.welkom_terug),
                style = TextStyles.title,
                modifier = Modifier.padding(bottom = Spacing.small, top = 70.dp)
            )
            Text(
                stringResource(R.string.aanmelden_welkom_tekst),
                style = TextStyles.text,
                modifier = Modifier.padding(bottom = 30.dp)
            )
            Column {
                TextFields.EmailTextField(
                    email = state.email,
                    isValid = true,
                    onInputChanged = { onAction(LoginScreenUiAction.OnEmailChangedAction(it)) },
                    modifier = Modifier.padding(horizontal = 15.dp).padding(bottom = 5.dp)
                )
                TextFields.PasswordTextField(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    onInputChanged = { onAction(LoginScreenUiAction.OnPasswordChangedAction(it)) },
                    onTogglePasswordVisibility = { onAction(LoginScreenUiAction.OnClickedPasswordVisibilityButtonAction) },
                    currentPassword = state.password,
                    isPasswordVisible = state.passwordVisibility,
                    isValid = true,
                )
                Text(
                    text = stringResource(R.string.wachtwoord_vergeten),
                    style = TextStyles.small_text,
                    modifier = Modifier
                        .padding(bottom = 30.dp, start = 15.dp)
                        .clickable { onAction(LoginScreenUiAction.OnClickedForgotPasswordButtonAction) }
                )
            }
            Buttons.PrimaryButton(
                text = stringResource(R.string.aanmelden),
                colors = ButtonDefaults.primaryButtonColors(),
                onClick = { onAction(LoginScreenUiAction.OnClickedLoginButtonAction) },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
            )
        }
    }
}