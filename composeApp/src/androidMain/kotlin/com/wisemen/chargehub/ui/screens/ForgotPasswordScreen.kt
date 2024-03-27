package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.TextFields
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.screens.destinations.LoginScreenDestination
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.Spacing
import com.wisemen.chargehub.ui.theme.TextStyles
import org.koin.java.KoinJavaComponent.inject
import screens.forgotpassword.ForgotPasswordUiAction
import screens.forgotpassword.ForgotPasswordUiEvent
import screens.forgotpassword.ForgotPasswordUiState
import screens.forgotpassword.ForgotPasswordViewModel
import com.wisemen.chargehub.ui.components.Buttons

@Preview
@Composable
fun ForgotPasswordPreview() {
    AppTheme {
        ForgotPasswordLayout(state = ForgotPasswordUiState(), onAction = {})
    }
}

@Composable
@Destination
@ChargeHubNavGraph
fun ForgotPasswordScreen(navController: NavController) {
    val viewModel: ForgotPasswordViewModel by inject(ForgotPasswordViewModel::class.java)

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is ForgotPasswordUiEvent.ClickedBackEvent -> navController.popBackStack()
                is ForgotPasswordUiEvent.ClickedSendEmailEvent -> navController.navigate(LoginScreenDestination)
            }
        }
    }

    val state = viewModel.state.collectAsState()
    ForgotPasswordLayout(state.value, viewModel::onAction)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordLayout(
    state: ForgotPasswordUiState,
    onAction: (ForgotPasswordUiAction) -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.forgot_password_title),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyles.topBarTitle,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(ForgotPasswordUiAction.OnClickedBackAction) },
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
                modifier = Modifier.padding(horizontal = Spacing.medium).padding(top = 70.dp, bottom = 30.dp),
                textAlign = TextAlign.Center,
                text = "Geef je e-mail adres in en volg de instructies om je wachtwoord te resetten.",
            )
            TextFields.EmailTextField(
                modifier = Modifier.padding(horizontal = Spacing.medium).padding(bottom = 5.dp),
                email = state.email,
                isValid = true,
                onInputChanged = { onAction(ForgotPasswordUiAction.OnEmailChangedAction(it)) },
            )
            Buttons.PrimaryButton(
                modifier = Modifier.fillMaxWidth().padding(horizontal = Spacing.medium),
                text = stringResource(R.string.send_mail),
                colors = ButtonDefaults.primaryButtonColors(),
                onClick = { onAction(ForgotPasswordUiAction.OnClickedSendEmailAction) },
            )
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}