package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.components.secondaryButtonColors
import com.wisemen.chargehub.ui.screens.destinations.LoginScreenDestination
import com.wisemen.chargehub.ui.screens.destinations.RegisterScreenDestination
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.Padding
import com.wisemen.chargehub.ui.theme.TextStyles
import org.koin.java.KoinJavaComponent.inject
import screens.landing.LandingScreenUiAction
import screens.landing.LandingScreenUiEvent
import screens.landing.LandingScreenViewModel

@Preview
@Composable
fun LandingScreenPreview() {
    AppTheme {
        LandingLayout {}
    }
}

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
                is LandingScreenUiEvent.ClickedLoginButtonEvent -> navController.navigate(
                    LoginScreenDestination
                )

                is LandingScreenUiEvent.ClickedRegisterButtonEvent -> navController.navigate(
                    RegisterScreenDestination
                )
            }
        }
    }
    LandingLayout(viewModel::onAction)
}

@Composable
fun LandingLayout(onAction: (LandingScreenUiAction) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.landingbackground),
            contentDescription = "background img",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier.padding(horizontal = Padding.medium)
        ) {
            Header()
            Spacer(Modifier.weight(1F))
            Buttons(onAction)
        }
    }
}

@Composable
fun Header() {
    Column(Modifier.padding(top = 46.dp)) {
        Text(
            stringResource(R.string.stroomline_your_day_with),
            style = TextStyles.bigTitle,
            color = Colors.white
        )
        Text(
            stringResource(R.string.charge),
            style = TextStyles.bigTitle,
            color = Colors.acid
        )
        Icon(
            painter = painterResource(R.drawable.logo_charge_hub),
            contentDescription = "logo",
            tint = Colors.acid,
            modifier = Modifier.size(74.dp)
        )
    }
}

@Composable
fun Buttons(onAction: (LandingScreenUiAction) -> Unit) {
    Row(Modifier.padding(bottom = 16.dp)) {
        Buttons.PrimaryButton(
            Modifier.fillMaxWidth(),
            text = stringResource(R.string.login),
            colors = ButtonDefaults.primaryButtonColors()
        ) {
            onAction(LandingScreenUiAction.ClickedLoginButtonAction)
        }
    }

    Row(Modifier.padding(bottom = 14.dp)) {
        Buttons.PrimaryButton(
            Modifier.fillMaxWidth(),
            text = stringResource(R.string.register),
            colors = ButtonDefaults.secondaryButtonColors()
        ) {
            onAction(LandingScreenUiAction.ClickedRegisterButtonAction)
        }
    }
}