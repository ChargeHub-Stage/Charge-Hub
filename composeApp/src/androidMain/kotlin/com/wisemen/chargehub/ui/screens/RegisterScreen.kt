package com.wisemen.chargehub.ui.screens

import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.TextStyles
import kotlinx.coroutines.launch
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
        CurrentRegisterState.CAR_CONNECT -> stringResource(R.string.voertuig)
        else -> {
            ""
        }
    }

    TopBar(topBarTitle, {
        onAction(RegisterScreenUiAction.OnPreviousClickedAction)
    }) {
        Column(Modifier.padding(it).padding(horizontal = 16.dp)) {
            when (state.currentRegisterState) {
                CurrentRegisterState.EMAIL -> EmailRegisterStep(state, onAction)
                CurrentRegisterState.PROFILE -> ProfileCompletionStep(state, onAction)
                CurrentRegisterState.CAR_CONNECT -> CarConnectStep(state, onAction)
                CurrentRegisterState.NOTIFICATIONS -> PermissionStep(onAction)
                CurrentRegisterState.INFO -> InfoStep(onAction)
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
            topLabel = stringResource(R.string.voornaam),
            trailingIcon = {
                ClearFieldIcon { onAction(RegisterScreenUiAction.OnFirstNameChangedAction("")) }
            }
        )

        TextFields.EditText(
            modifier = Modifier.padding(bottom = 21.dp),
            input = state.lastName,
            onInputChanged = { onAction(RegisterScreenUiAction.OnLastNameChangedAction(it)) },
            topLabel = stringResource(R.string.achternaam),
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
fun CarConnectStep(state: RegisterScreenUiState, onAction: (RegisterScreenUiAction) -> Unit) {

    TextFields.EditText(
        modifier = Modifier.padding(top = 34.dp, bottom = 16.dp),
        input = state.carId,
        onInputChanged = { onAction(RegisterScreenUiAction.OnCarIdChangedAction(it)) },
        topLabel = stringResource(R.string.jouw_auto_id),
        trailingIcon = { ClearFieldIcon { onAction(RegisterScreenUiAction.OnCarIdChangedAction("")) } }
    )

    NextButton(onAction)

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
            text = stringResource(R.string.meldingen_toestaan),
            style = TextStyles.mediumTitle,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.padding(bottom = 43.dp),
            text = stringResource(R.string.meldingen_info),
            textAlign = TextAlign.Center
        )

        PrimaryButton(
            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth(),
            text = stringResource(R.string.meldingen_toestaan),
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
fun InfoStep(onAction: (RegisterScreenUiAction) -> Unit) {

    @Composable
    fun InfoTitle(title: String) {
        Text(
            modifier = Modifier.padding(bottom = 19.06.dp),
            text = title,
            style = TextStyles.mediumTitle
        )
    }

    @Composable
    fun InfoPageOne() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            InfoTitle(stringResource(R.string.laadbeurten))
            Image(
                modifier = Modifier
                    .padding(bottom = 72.97.dp)
                    .width(147.82.dp)
                    .height(135.96.dp),
                painter = painterResource(R.drawable.laadbeurten_info),
                contentDescription = null
            )
            Text(text = stringResource(R.string.reservatie_info), style = TextStyles.infoSubTitle)
        }
    }

    @Composable
    fun InfoPageTwo() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            InfoTitle(title = stringResource(R.string.laadbeurt_afstaan))
            Image(
                modifier = Modifier.padding(bottom = 28.dp).width(174.dp).height(211.dp),
                painter = painterResource(R.drawable.laadbeurt_afstaan),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.laadbeurt_afstaan_info),
                style = TextStyles.infoSubTitle
            )
        }
    }

    @Composable
    fun InfoPageThree() {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            InfoTitle(stringResource(R.string.level_systeem))
            Image(
                modifier = Modifier.padding(bottom = 28.dp).width(294.dp).height(41.dp),
                painter = painterResource(R.drawable.progress_bar),
                contentDescription = null
            )
            Text(text = stringResource(R.string.level_system_title), style = TextStyles.infoTitle)
            Text(text = stringResource(R.string.stiptheid), style = TextStyles.infoSubTitle)
            Text(
                modifier = Modifier.padding(bottom = 16.dp),
                text = stringResource(R.string.stiptheid_details),
                style = TextStyles.infoText
            )

            Text(text = stringResource(R.string.hoffelijkheid), style = TextStyles.infoSubTitle)
            Text(
                text = stringResource(R.string.hoffelijkheid_details),
                style = TextStyles.infoText
            )
        }
    }

    @Composable
    fun InfoPageFour() {

    }

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

        NextButton {
            // If you reach the last page, finalise the registering process
            // Else, go to the next
            if (currentPage == pagerState.pageCount) {
                onAction(action)
            } else {
                coroutine.launch {
                    pagerState.scrollToPage(currentPage + 1)
                }
            }
        }

        SkipTextButton(
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp),
            textAlign = TextAlign.Center
        ) { onAction(action) }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val pageCount = pagerState.pageCount
    val currentPage = pagerState.currentPage
    val coroutine = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.Center
        ) {
            items(pageCount) { pageIndex ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 11.dp)
                        .width(82.dp)
                        .height(9.dp)
                        .background(
                            color = if (pageIndex == currentPage) Colors.acid else Colors.lightGrey,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable {
                            coroutine.launch {
                                pagerState.scrollToPage(pageIndex)
                            }
                        }
                )
            }
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
        text = stringResource(R.string.volgende),
        onClick = {
            onAction(RegisterScreenUiAction.OnNextClickedAction)
        },
        textStyle = TextStyles.boldText,
        colors = ButtonDefaults.primaryButtonColors()
    )
}

@Composable
fun SkipTextButton(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    onAction: () -> Unit
) {
    Text(
        modifier = modifier.clickable { onAction() },
        text = stringResource(R.string.overslaan),
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.W700,
        textAlign = textAlign
    )
}
