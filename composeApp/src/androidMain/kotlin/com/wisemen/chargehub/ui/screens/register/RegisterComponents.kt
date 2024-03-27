package com.wisemen.chargehub.ui.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import com.wisemen.chargehub.R
import com.wisemen.chargehub.SharedRes
import com.wisemen.chargehub.toStringResource
import com.wisemen.chargehub.ui.components.Buttons
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.theme.TextStyles
import data.CurrentRegisterState
import screens.register.RegisterScreenUiAction
import screens.register.RegisterScreenUiState

@Composable
fun NextButton(state: RegisterScreenUiState, onAction: (RegisterScreenUiAction) -> Unit) {
    val shouldButtonEnable = when (state.currentRegisterState) {
        CurrentRegisterState.EMAIL -> state.isEmailStepValid()
        CurrentRegisterState.PROFILE -> state.isProfileStepValid()
        CurrentRegisterState.CAR_CONNECT -> state.isVinStepValid()
        CurrentRegisterState.INFO -> true
        else -> false
    }
    Buttons.PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = SharedRes.strings.next.toStringResource(),
        onClick = {
            onAction(RegisterScreenUiAction.OnNextClickedAction)
        },
        enabled = shouldButtonEnable,
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
        text = SharedRes.strings.skip.toStringResource(),
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.W700,
        textAlign = textAlign
    )
}
