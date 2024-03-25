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
import com.wisemen.chargehub.ui.components.Buttons
import com.wisemen.chargehub.ui.components.primaryButtonColors
import com.wisemen.chargehub.ui.theme.TextStyles
import screens.register.RegisterScreenUiAction

@Composable
fun NextButton(onAction: (RegisterScreenUiAction) -> Unit) {
    Buttons.PrimaryButton(
        modifier = Modifier.fillMaxWidth(),
        text = stringResource(R.string.next),
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
        text = stringResource(R.string.skip),
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.W700,
        textAlign = textAlign
    )
}
