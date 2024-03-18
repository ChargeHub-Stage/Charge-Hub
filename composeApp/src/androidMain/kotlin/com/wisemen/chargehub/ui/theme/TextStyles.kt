package com.wisemen.chargehub.ui.theme

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

    val bigTitle = TextStyle(
        fontSize = 69.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 60.sp,
    )

    val title = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 34.sp,
        letterSpacing = 0.36.sp
    )

    val text = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 22.sp,
        letterSpacing = (- 0.41).sp
    )

    var topBarTitle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 22.sp,
        letterSpacing = (- 0.41).sp
    )

    var small_text = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 14.32.sp,
    )
}