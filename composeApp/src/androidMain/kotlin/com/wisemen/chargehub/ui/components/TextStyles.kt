package com.wisemen.chargehub.ui.components

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.wisemen.chargehub.ui.theme.Colors

object TextStyles {

    val topLabelText = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 16.71.sp
    )

    val hint = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 19.09.sp,
    )

    val error = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 14.31.sp,
        color = Colors.rebel
    )

}