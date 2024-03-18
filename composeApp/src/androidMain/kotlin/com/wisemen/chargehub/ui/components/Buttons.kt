package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wisemen.chargehub.ui.components.Buttons.IconButton
import com.wisemen.chargehub.ui.components.Buttons.PrimaryButton
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors

@Composable
@Preview
private fun ButtonPreview() {
    AppTheme {
        Column {
            IconButton(
                onClick = {},
                icon = { Icon(Icons.Default.Add, "") })
            PrimaryButton(
                onClick = {},
                text = "click",
                colors = ButtonDefaults.primaryButtonColors(),
                trailingIcon = { Icon(Icons.Default.Add, "") })
        }
    }
}

object Buttons {

    @Composable
    fun TextOnlyButton(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        text: String,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        textStyle: TextStyle = TextStyle.Default,
        textColor: Color = Colors.blackPearl,
        onClick: () -> Unit,
    ) {
        Button(
            enabled = enabled,
            modifier = modifier,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = textColor
            ),
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
            contentPadding = contentPadding,
            onClick = onClick
        ) {
            Text(text = text, style = textStyle)
        }
    }

    @Composable
    fun PrimaryButton(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        text: String,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        textStyle: TextStyle = TextStyle.Default,
        onClick: () -> Unit,
        trailingIcon: @Composable (() -> Unit)? = null,
    ) {
        Button(
            enabled = enabled,
            modifier = modifier,
            shape = RoundedCornerShape(5.dp),
            colors = colors,
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
            contentPadding = contentPadding,
            onClick = onClick,
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = text, style = textStyle, modifier = Modifier.align(Alignment.CenterVertically))
                Spacer(Modifier.weight(1F))
                trailingIcon?.let { it() }
            }
        }
    }

    @Composable
    fun IconButton(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        icon: @Composable (() -> Unit),
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        onClick: () -> Unit,
    ) {
        Button(
            enabled = enabled,
            modifier = modifier,
            shape = RoundedCornerShape(5.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Colors.blackPearl
            ),
            contentPadding = contentPadding,
            elevation = ButtonDefaults.noElevation(),
            onClick = onClick
        ) {
            icon()
        }
    }

}

@Composable
fun ButtonDefaults.noElevation(): ButtonElevation = buttonElevation(0.dp, 0.dp, 0.dp)

@Composable
fun ButtonDefaults.primaryButtonColors(): ButtonColors = buttonColors(
    contentColor = Colors.blackPearl,
    containerColor = Colors.acid,
    disabledContentColor = Colors.blackPearl,
    disabledContainerColor = Colors.lightGrey
)

@Composable
fun ButtonDefaults.secondaryButtonColors(): ButtonColors = buttonColors(
    contentColor = Colors.white,
    containerColor = Colors.smoke,
    disabledContentColor = Colors.blackPearl,
    disabledContainerColor = Colors.lightGrey
)