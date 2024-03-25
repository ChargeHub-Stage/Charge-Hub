package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons.PrimaryButton
import com.wisemen.chargehub.ui.components.TextFields
import com.wisemen.chargehub.ui.components.TopBar
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.screens.destinations.LandingScreenDestination
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.TextStyles
import org.koin.java.KoinJavaComponent
import screens.register.CurrentRegisterState
import screens.register.RegisterScreenUiAction
import screens.register.RegisterScreenUiEvent
import screens.register.RegisterScreenUiState
import screens.register.RegisterScreenViewModel

@Preview
@Composable
fun RegisterScreenPreview() {
    AppTheme {
        LandingLayout {}
    }
}

@Composable
@Destination
@ChargeHubNavGraph
fun RegisterScreen(
    navController: NavController
) {
    val viewModel: RegisterScreenViewModel by KoinJavaComponent.inject(RegisterScreenViewModel::class.java)
    val state = viewModel.state.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is RegisterScreenUiEvent.OnNextClickedEvent -> {}
                is RegisterScreenUiEvent.OnPreviousClickedEvent -> navController.navigate(
                    LandingScreenDestination
                )
            }
        }
    }
    RegisterLayout(viewModel::onAction, state.value)
}

@Composable
fun RegisterLayout(
    onAction: (RegisterScreenUiAction) -> Unit,
    state: RegisterScreenUiState
) {

    val topBarTitle = when (state.currentRegisterState) {
        CurrentRegisterState.EMAIL -> stringResource(R.string.e_mail)
        CurrentRegisterState.CAR_CONNECT -> stringResource(R.string.vehicle)
        else -> {""}
    }

    TopBar(topBarTitle, {
        onAction(RegisterScreenUiAction.OnPreviousClickedAction)
    }) {
        Column(Modifier.padding(it).padding(horizontal = 16.dp)) {

            when (state.currentRegisterState) {
                CurrentRegisterState.EMAIL -> EmailRegisterStep(state, onAction)
                else -> {}
            }

        }
    }
}

@Composable
fun EmailRegisterStep(state: RegisterScreenUiState, onAction: (RegisterScreenUiAction) -> Unit) {
    TextFields.EmailTextField(
        modifier = Modifier.padding(top = 30.dp),
        email = state.email,
        isValid = state.isEmailValid,
        onInputChanged = { newEmail ->
            onAction(
                RegisterScreenUiAction.OnEmailChangedAction(
                    newEmail
                )
            )
        }
    )
    Text(
        modifier = Modifier.padding(top = 4.dp, bottom = 27.dp),
        text = stringResource(R.string.privacybeleid),
        style = TextStyles.bottomLabel
    )

    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.next),
        onClick = {
            onAction(RegisterScreenUiAction.OnNextClickedAction)
        },
        colors = ButtonDefaults.primaryButtonColors()
    )
}