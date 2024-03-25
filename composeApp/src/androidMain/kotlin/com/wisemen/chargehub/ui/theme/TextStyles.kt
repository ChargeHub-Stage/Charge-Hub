package com.wisemen.chargehub.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

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

    val mediumTitle = TextStyle(
        fontSize = 28.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 34.sp
    )

    val topBarTitle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 22.sp
    )

    val bottomLabel = TextStyle(
        fontSize = 12.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 12.32.sp
    )

    val boldText = TextStyle(
        fontWeight = FontWeight.W700
    )

    val infoTitle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 25.sp,
        color = Colors.blackPearl,
        textAlign = TextAlign.Center
    )


    val infoSubTitle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 22.sp,
        color = Colors.mediumGray,
        textAlign = TextAlign.Center
    )

    val infoText = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 22.sp,
        color = Colors.mist,
        textAlign = TextAlign.Center
    )

    val levelExplanation = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 22.sp,
        textAlign = TextAlign.Center
    )
    val levelTitle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 25.sp,
        textAlign = TextAlign.Center
    )
}