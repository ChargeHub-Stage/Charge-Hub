package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons
import org.koin.java.KoinJavaComponent.inject
import screens.landing.LandingScreenUiAction
import screens.landing.LandingScreenUiEvent
import screens.landing.LandingScreenViewModel

@Composable
@Destination
@ChargeHubNavGraph(start = true)
fun LandingScreen(
    navController: NavController
) {
    val viewModel: LandingScreenViewModel by inject(LandingScreenViewModel::class.java)

    LaunchedEffect(viewModel) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is LandingScreenUiEvent.ClickedLoginButtonEvent -> {}
                is LandingScreenUiEvent.ClickedRegisterButtonEvent -> {}
            }
        }
    }
    LandingLayout(viewModel::onAction)
}

@Composable
fun LandingLayout(onAction: (LandingScreenUiAction) -> Unit) {
    Column(modifier = Modifier.padding(10.dp)) {
        Buttons.AppButton(
            text = "Login"
        ) {
            onAction(LandingScreenUiAction.ClickedLoginButtonAction)
        }
        Buttons.AppButton(
            text = "Register"
        ) {
            onAction(LandingScreenUiAction.ClickedRegisterButtonAction)
        }
    }
}