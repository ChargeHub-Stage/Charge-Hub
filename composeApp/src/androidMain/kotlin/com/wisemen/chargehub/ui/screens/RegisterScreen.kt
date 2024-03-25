package com.wisemen.chargehub.ui.screens

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.wisemen.chargehub.ui.screens.register.BottomPagerIndicator
import com.wisemen.chargehub.ui.screens.register.InfoPageFour
import com.wisemen.chargehub.ui.screens.register.InfoPageOne
import com.wisemen.chargehub.ui.screens.register.InfoPageThree
import com.wisemen.chargehub.ui.screens.register.InfoPageTwo
import com.wisemen.chargehub.ui.screens.register.InfoTitle
import com.wisemen.chargehub.ui.screens.register.NextButton
import com.wisemen.chargehub.ui.screens.register.ShortInfoPage
import com.wisemen.chargehub.ui.screens.register.SkipTextButton
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.Padding
import com.wisemen.chargehub.ui.theme.TextStyles
import kotlinx.coroutines.launch
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
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
                CurrentRegisterState.CAR_CONNECT -> CarConnectStep(state, onAction)
                CurrentRegisterState.NOTIFICATIONS -> PermissionStep(onAction)
                CurrentRegisterState.INFO -> InfoStep(state, onAction)
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
        },
    )
    Text(
        modifier = Modifier.padding(top = 4.dp, bottom = 27.dp),
        text = stringResource(R.string.privacy),
        style = TextStyles.bottomLabel
    )
    NextButton(state, onAction)
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
            },
            errorMessage = if (state.isFirstNameValid) null else stringResource(R.string.first_name_validation_error)
        )

        TextFields.EditText(
            modifier = Modifier.padding(bottom = 21.dp),
            input = state.lastName,
            onInputChanged = { onAction(RegisterScreenUiAction.OnLastNameChangedAction(it)) },
            topLabel = stringResource(R.string.lastname),
            trailingIcon = {
                ClearFieldIcon { onAction(RegisterScreenUiAction.OnLastNameChangedAction("")) }
            },
            errorMessage = if (state.isLastNameValid) null else stringResource(R.string.last_name_validation_error)

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
        PrivacySwitch(
            modifier = Modifier.align(Alignment.Start).padding(bottom = 26.dp),
            isChecked = state.isPrivacyChecked,
            onCheckedChange = { onAction(RegisterScreenUiAction.OnPrivacyCheckedChangedAction) })

        NextButton(state, onAction)
    }
}

@Composable
fun CarConnectStep(state: RegisterScreenUiState, onAction: (RegisterScreenUiAction) -> Unit) {

    TextFields.EditText(
        modifier = Modifier.padding(top = 34.dp, bottom = 16.dp),
        input = state.vin,
        onInputChanged = { onAction(RegisterScreenUiAction.OnCarIdChangedAction(it)) },
        topLabel = stringResource(R.string.your_car_id),
        trailingIcon = { ClearFieldIcon { onAction(RegisterScreenUiAction.OnCarIdChangedAction("")) } },
        errorMessage = if (state.isVinValid) null else stringResource(R.string.invalid_car_id)
    )

    PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.next),
        onClick = {
            //if (state.isCarIdValid) {
                onAction(RegisterScreenUiAction.OnNextClickedAction)
            //}
        },
        colors = ButtonDefaults.primaryButtonColors()
    )

}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun PermissionStep(onAction: (RegisterScreenUiAction) -> Unit) {
    
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { _: Boolean ->
        onAction(RegisterScreenUiAction.OnNextClickedAction)
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 89.dp),
            text = stringResource(R.string.allow_notis),
            style = TextStyles.mediumTitle,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 43.dp),
            text = stringResource(R.string.noti_info),
            textAlign = TextAlign.Center
        )

        PrimaryButton(
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth(),
            text = stringResource(R.string.allow_notis),
            colors = ButtonDefaults.primaryButtonColors(),
            textStyle = TextStyles.boldText,
        ) {
            permissionLauncher.launch(android.Manifest.permission.POST_NOTIFICATIONS)
        }

        SkipTextButton { onAction(RegisterScreenUiAction.OnNextClickedAction) }


    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun InfoStep(state: RegisterScreenUiState, onAction: (RegisterScreenUiAction) -> Unit) {

    val pagerState = rememberPagerState(pageCount = { 4 })
    val currentPage = pagerState.currentPage
    val coroutine = rememberCoroutineScope()
    val action = RegisterScreenUiAction.OnFinaliseRegisterAction

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) { page ->
            when (page) {
                0 -> InfoPageOne()
                1 -> InfoPageTwo()
                2 -> InfoPageThree()
                3 -> InfoPageFour()
            }
        }

        BottomPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier.padding(top = 130.dp)
        )

        NextButton(state) {
            // If you reach the last page, finalise the registering process
            // Else, go to the next
            if (currentPage + 1 >= pagerState.pageCount) {
                onAction(action)
            } else {
                coroutine.launch {
                    pagerState.scrollToPage(currentPage + 1)
                }
            }
        }

        if (currentPage + 1 != pagerState.pageCount) {
            SkipTextButton(
                modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
                textAlign = TextAlign.Center
            ) { onAction(action) }
        }
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
fun PrivacySwitch(
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
