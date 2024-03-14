package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wisemen.chargehub.ui.components.Buttons.AppButton
import com.wisemen.chargehub.ui.components.Buttons.IconButton
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors

@Composable
@Preview
private fun ButtonPreview() {
    AppTheme {
        Column {
            IconButton(onClick = {}, icon = { Icon(Icons.Default.Add, "") })
            AppButton(
                onClick = {}, text = "click", colors = ButtonDefaults.buttonColors()
                    .copy(containerColor = Colors.acid, contentColor = Colors.white)
            )
        }
        //TextButton(onClick = {}, text = "Hey")

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
    fun AppButton(
        modifier: Modifier = Modifier,
        enabled: Boolean = true,
        text: String,
        contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
        colors: ButtonColors = ButtonDefaults.buttonColors(),
        textStyle: TextStyle = TextStyle.Default,
        onClick: () -> Unit,
    ) {
        Button(
            enabled = enabled,
            modifier = modifier,
            shape = RoundedCornerShape(5.dp),
            colors = colors,
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
            contentPadding = contentPadding,
            onClick = onClick
        ) {
            Text(text = text, style = textStyle)
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
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp),
            onClick = onClick
        ) {
            icon()
        }
    }

}