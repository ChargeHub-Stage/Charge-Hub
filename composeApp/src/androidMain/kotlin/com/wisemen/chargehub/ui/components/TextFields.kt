package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wisemen.chargehub.R
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors

@Preview(showBackground = true)
@Composable
fun ErrorPreview() {
    AppTheme {
        Column {
            TextFields.EditText(
                input = "Test",
                topLabel = "First Name",
                onInputChanged = { _ -> },
                errorMessage = "First name is required"
            )

            TextFields.EmailTextField(email = "test@test.com", isValid = true, onInputChanged = {})
            TextFields.PasswordTextField(
                currentPassword = "test@test.com",
                isValid = true,
                onInputChanged = {},
                onTogglePasswordVisibility = { },
                isPasswordVisible = false
            )
        }
    }
}

object TextFields {

    @Composable
    fun EditText(
        modifier: Modifier = Modifier,
        input: String,
        onInputChanged: (String) -> Unit,
        topLabel: String? = null,
        enabled: Boolean = true,
        errorMessage: String? = null,
        colors: TextFieldColors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.White,
            errorContainerColor = Color.White,
            errorLeadingIconColor = Color.Black,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            errorBorderColor = Color.Red
        ),
        textBoxHeight: Dp = OutlinedTextFieldDefaults.MinHeight,
        visualTransformation: VisualTransformation = VisualTransformation.None,
        trailingIcon: @Composable (() -> Unit)? = null,
        leadingIcon: @Composable (() -> Unit)? = null,
        hint: String = stringResource(R.string.type_here),
        maxLines: Int = Int.MAX_VALUE,
    ) {

        Column(modifier) {
            topLabel?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(bottom = 5.dp),
                    style = TextStyles.topLabelText
                )
            }

            OutlinedTextField(
                modifier = Modifier
                    .height(textBoxHeight)
                    .fillMaxWidth(),
                isError = errorMessage != null,
                placeholder = {
                    Text(
                        text = hint,
                        style = TextStyles.hint
                    )
                },
                maxLines = maxLines,
                visualTransformation = visualTransformation,
                enabled = enabled,
                shape = RoundedCornerShape(10.dp),
                colors = colors,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                value = input,
                onValueChange = onInputChanged,
            )

            errorMessage?.let {
                Row(
                    modifier = Modifier.padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(10.5.dp),
                        imageVector = Icons.Outlined.ErrorOutline,
                        contentDescription = null,
                        tint = Colors.rebel
                    )
                    Text(text = it, style = TextStyles.error)
                }
            }
        }
    }

    @Composable
    fun PasswordTextField(
        modifier: Modifier = Modifier,
        onInputChanged: (String) -> Unit,
        onTogglePasswordVisibility: () -> Unit,
        currentPassword: String,
        isPasswordVisible: Boolean,
        isValid: Boolean,
    ) {

        Box(modifier, contentAlignment = Alignment.CenterEnd) {
            EditText(
                errorMessage = if (!isValid) stringResource(R.string.password_is_required) else null,
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                topLabel = stringResource(R.string.password),
                input = currentPassword,
                onInputChanged = {
                    onInputChanged(it)
                },
                trailingIcon = {
                    val iconClick =
                        Modifier.clickable { onTogglePasswordVisibility() }

                    if (isPasswordVisible) {
                        Icon(
                            Icons.Outlined.VisibilityOff,
                            null,
                            iconClick,
                            tint = Colors.blackPearl
                        )
                    } else Icon(
                        Icons.Outlined.Visibility,
                        contentDescription = null,
                        iconClick,
                        tint = Colors.blackPearl
                    )
                },
                maxLines = 1,
            )
        }
    }

    @Composable
    fun EmailTextField(
        modifier: Modifier = Modifier,
        email: String,
        isValid: Boolean,
        onInputChanged: (String) -> Unit,
    ) {
        EditText(
            modifier = modifier,
            errorMessage = if (!isValid) stringResource(R.string.email_is_required) else null,
            topLabel = stringResource(id = R.string.email),
            input = email,
            onInputChanged = {
                onInputChanged(it)
            },
            maxLines = 1,
        )
    }

}