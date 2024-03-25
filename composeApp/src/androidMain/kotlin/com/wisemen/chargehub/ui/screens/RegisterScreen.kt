package com.wisemen.chargehub.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.wisemen.chargehub.R
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.components.Buttons.PrimaryButton
import com.wisemen.chargehub.ui.components.TextFields
import com.wisemen.chargehub.ui.components.TopBar
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.Padding
import com.wisemen.chargehub.ui.theme.TextStyles
import data.CurrentRegisterState
import org.koin.java.KoinJavaComponent
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
                is RegisterScreenUiEvent.OnPreviousClickedEvent -> {}
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
        CurrentRegisterState.PROFILE -> stringResource(R.string.profile)
        CurrentRegisterState.CAR_CONNECT -> stringResource(R.string.car)
        else -> {
            ""
        }
    }

    TopBar(topBarTitle, {
        onAction(RegisterScreenUiAction.OnPreviousClickedAction)
    }) {
        Column(Modifier.padding(it).padding(horizontal = Padding.medium)) {
            when (state.currentRegisterState) {
                CurrentRegisterState.EMAIL -> EmailRegisterStep(state, onAction)
                CurrentRegisterState.PROFILE -> ProfileCompletionStep(state, onAction)
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
        text = stringResource(R.string.privacy),
        style = TextStyles.bottomLabel
    )
    NextButton(onAction)
}

@Composable
fun ProfileCompletionStep(
    state: RegisterScreenUiState,
    onAction: (RegisterScreenUiAction) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProfilePicture(
            modifier = Modifier.padding(bottom = 30.dp),
            painter = painterResource(R.drawable.logo)
        )

        TextFields.EditText(
            modifier = Modifier.padding(bottom = 21.dp),
            input = state.firstName,
            onInputChanged = { onAction(RegisterScreenUiAction.OnFirstNameChangedAction(it)) },
            topLabel = stringResource(R.string.firstname),
            trailingIcon = {
                ClearFieldIcon { onAction(RegisterScreenUiAction.OnFirstNameChangedAction("")) }
            }
        )

        TextFields.EditText(
            modifier = Modifier.padding(bottom = 21.dp),
            input = state.lastName,
            onInputChanged = { onAction(RegisterScreenUiAction.OnLastNameChangedAction(it)) },
            topLabel = stringResource(R.string.lastname),
            trailingIcon = {
                ClearFieldIcon { onAction(RegisterScreenUiAction.OnLastNameChangedAction("")) }
            }
        )

        TextFields.PasswordTextField(
            modifier = Modifier.padding(bottom = 17.dp),
            currentPassword = state.password,
            isPasswordVisible = false,
            isValid = state.isPasswordValid,
            onTogglePasswordVisibility = {},
            onInputChanged = { onAction(RegisterScreenUiAction.OnPasswordChangedAction(it)) },
            trailingIcon = {
                ClearFieldIcon {
                    onAction(
                        RegisterScreenUiAction.OnPasswordChangedAction(
                            ""
                        )
                    )
                }
            }
        )
        PrivacySlider(
            modifier = Modifier.align(Alignment.Start).padding(bottom = 26.dp),
            isChecked = state.isPrivacyChecked,
            onCheckedChange = { onAction(RegisterScreenUiAction.OnPrivacyCheckedChangedAction) })

        NextButton(onAction)
    }
}

@Composable
fun ClearFieldIcon(
    onAction: () -> Unit
) {
    Icon(
        modifier = Modifier.clickable { onAction() },
        imageVector = Icons.Filled.Clear,
        contentDescription = "clear"
    )
}

@Composable
fun PrivacySlider(
    modifier: Modifier = Modifier,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    var sliderState by remember { mutableStateOf(isChecked) }
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Switch(
            modifier = Modifier.align(Alignment.CenterVertically),
            checked = sliderState,
            colors = SwitchDefaults.colors().copy(
                checkedTrackColor = Colors.acid,
                uncheckedTrackColor = Colors.lightGrey,
                uncheckedIconColor = Colors.white
            ),
            onCheckedChange = {
                sliderState = !sliderState
                onCheckedChange(it)
            })
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = stringResource(R.string.privacy1),
            style = TextStyles.bottomLabel
        )

        val uriHandler = LocalUriHandler.current
        Text(
            modifier = Modifier.clickable { uriHandler.openUri("https://policies.google.com/privacy") },
            text = stringResource(R.string.privacy2),
            textDecoration = TextDecoration.Underline,
            style = TextStyles.bottomLabel,
        )
        Text(stringResource(R.string.privacy3), style = TextStyles.bottomLabel)
    }
}

@Composable
fun CircularProfilePicture(
    painter: Painter,
    modifier: Modifier = Modifier,
    borderColor: Color = Color.White,
    borderWidth: Dp = 4.dp,
    contentDescription: String? = null,
) {
    Box(
        modifier = modifier
            .padding(top = 9.dp)
            .size(100.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .border(width = borderWidth, color = borderColor, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = contentDescription,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun NextButton(onAction: (RegisterScreenUiAction) -> Unit) {
    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.next),
        onClick = {
            onAction(RegisterScreenUiAction.OnNextClickedAction)
        },
        colors = ButtonDefaults.primaryButtonColors()
    )
}

