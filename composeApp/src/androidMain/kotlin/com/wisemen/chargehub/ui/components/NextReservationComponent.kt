package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemen.chargehub.R
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors
import org.w3c.dom.Text

@Preview(showBackground = true)
@Composable
fun UpcomingReservation(
    chargehubName: String = "Carport",
    date: String = "28 September",
    startTime: String = "Vandaag",
    startHour: String = "11:00",
    endHour: String = "14:00"
) {
    AppTheme {
        ReservationBox {
            Row {

                Column(
                    Modifier.padding(start = 12.dp, top = 12.dp),
                ) {
                    Row {
                        IconDisplay()

                        Column(modifier = Modifier.padding(start = 4.35.dp)) {
                            Text(
                                text = stringResource(R.string.laadpaal, chargehubName),
                                style = TextStyles.nextReservationCardTitle
                            )
                            Text(text = stringResource(R.string._28_september), style = TextStyles.nextReservationCardSubTitle)
                        }
                        Spacer(modifier = Modifier.weight(1F))
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForwardIos,
                            "",
                            Modifier.padding(bottom = 11.dp, end = 6.dp),
                            tint = Colors.white
                        )
                    }

                    Column(Modifier.padding(bottom = 8.dp, top = 11.dp)) {
                        Row {
                            TimeSlotInformation(startHour, endHour)
                            Spacer(Modifier.weight(1F))
                            StartTimeInformation(startTime)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun StartTimeInformation(startTime: String) {
    Column {
        Text(
            modifier = Modifier.padding(end = 44.69.dp),
            text = stringResource(R.string.start),
            style = TextStyles.nextReservationHeader
        )
        Text(
            modifier = Modifier.padding(end = 44.69.dp),
            text = startTime,
            style = TextStyles.nextReservationText
        )
    }
}

@Composable
fun TimeSlotInformation(startHour: String, endHour: String) {
    Column {
        Text(
            text = stringResource(R.string.jouw_tijdslot),
            style = TextStyles.nextReservationHeader
        )
        Text(text = stringResource(
            R.string.start_end_hour,
            startHour,
            endHour
        ), style = TextStyles.nextReservationText)
    }
}

@Composable
fun IconDisplay() {
    Box(contentAlignment = Alignment.Center) {
        Icon(
            painter = painterResource(id = R.drawable.ellipse),
            contentDescription = "",
            tint = Colors.white
        )
        Icon(
            painter = painterResource(id = R.drawable.chargehub_icon),
            contentDescription = null,
            tint = Colors.white
        )
    }
}
@Composable
fun ReservationBox(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Colors.blackPearl)
            .height(108.dp)
    ) {
        content()
    }
}