package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wisemen.chargehub.R
import com.wisemen.chargehub.ui.theme.AppTheme
import com.wisemen.chargehub.ui.theme.Colors

@Preview(showBackground = true)
@Composable
fun TimeSlot(
    start: String = "11:00",
    end: String = "14:00",
    duration: Int = 3,
    chargeHubName: String = "Carport",
    dateDay: String = "28",
    dateMonth: String = "Sep",
    onArrowIconClicked: () -> Unit = {}
) {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Colors.catSkillWhite)
                .height(82.dp)
                .clip(RoundedCornerShape(4.dp))
        ) {
            Row {
                Column(
                    Modifier
                        .background(Colors.acid)
                        .fillMaxHeight()
                        .height(82.dp)
                        .width(56.31.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = dateDay, style = dateDayTextStyle)
                    Text(text = dateMonth, style = dateMonthTextStyle)
                }

                Column(
                    Modifier.padding(start = 12.dp, top = 10.dp),
                ) {
                    Row {
                        Text(
                            text = stringResource(R.string.laadpaal, chargeHubName),
                            style = cardTitleStyle
                        )
                        Spacer(modifier = Modifier.weight(1F))
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowForwardIos,
                            null,
                            Modifier.padding(bottom = 11.dp, end = 6.dp).clickable { onArrowIconClicked() }
                        )
                    }

                    Column {
                        Row {
                            Column {
                                Text(
                                    text = stringResource(R.string.jouw_tijdslot),
                                    style = headerTextStyle
                                )
                                Text(
                                    text = stringResource(
                                        R.string.timeslot_duration_start_end,
                                        start,
                                        end
                                    ), style = textTextStyle
                                )
                            }

                            Spacer(Modifier.weight(1F))

                            VerticalDivider(
                                modifier = Modifier.height(14.dp),
                                color = Color(0XFF888888)
                            )

                            Spacer(Modifier.weight(1F))

                            Column {
                                Text(
                                    modifier = Modifier.padding(end = 44.69.dp),
                                    text = stringResource(R.string.oplaadtijd),
                                    style = headerTextStyle
                                )
                                Text(
                                    modifier = Modifier.padding(end = 44.69.dp),
                                    text = stringResource(R.string.uur, duration),
                                    style = textTextStyle
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private val textTextStyle = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 11.93.sp,
    color = Colors.blackPearl
)

private val headerTextStyle = TextStyle(
    fontSize = 10.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 20.29.sp,
    color = Color(0XFF888888)
)

private val cardTitleStyle = TextStyle(
    fontSize = 17.sp,
    fontWeight = FontWeight.W600,
    lineHeight = 20.29.sp
)

private val dateDayTextStyle = TextStyle(
    fontSize = 21.sp,
    fontWeight = FontWeight.W700,
    lineHeight = 25.sp
)

private val dateMonthTextStyle = TextStyle(
    fontSize = 14.sp,
    fontWeight = FontWeight.W400,
    lineHeight = 16.71.sp
)

