package com.wisemen.chargehub.ui.components

import androidx.compose.ui.graphics.Color
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

    val nextReservationText = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 25.sp,
        color = Colors.white
    )

    val nextReservationHeader = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.29.sp,
        color = Color(0XFF999999)
    )

    val nextReservationCardTitle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 22.sp,
        color = Colors.acid
    )

    val nextReservationCardSubTitle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 18.sp,
        color = Colors.white
    )

    val timeSlotText = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 11.93.sp,
        color = Colors.blackPearl
    )

    val timeSlotHeader = TextStyle(
        fontSize = 10.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 20.29.sp,
        color = Colors.mist
    )

    val timeSlotCardTitle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 20.29.sp
    )

    val timeSlotDateDay = TextStyle(
        fontSize = 21.sp,
        fontWeight = FontWeight.W700,
        lineHeight = 25.sp
    )

    val timeSlotDateMonth = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.W400,
        lineHeight = 16.71.sp
    )

}