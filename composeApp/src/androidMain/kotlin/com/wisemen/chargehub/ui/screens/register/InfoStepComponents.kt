package com.wisemen.chargehub.ui.screens.register

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wisemen.chargehub.R
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.TextStyles
import kotlinx.coroutines.launch

@Composable
fun InfoTitle(title: String) {
    Text(
        modifier = Modifier.padding(bottom = 19.06.dp),
        text = title,
        style = TextStyles.mediumTitle
    )
}

@Composable
fun ShortInfoPage(title: String, imageResId: Int, descriptionResId: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        InfoTitle(title = title)
        Image(
            modifier = Modifier.padding(bottom = 16.dp),
            painter = painterResource(imageResId),
            contentDescription = null
        )
        Text(
            text = stringResource(descriptionResId),
            style = TextStyles.infoSubTitle,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun InfoPageOne() {
    ShortInfoPage(
        title = stringResource(R.string.charges),
        imageResId = R.drawable.charge_info,
        descriptionResId = R.string.charges_info
    )
}

@Composable
fun InfoPageTwo() {
    ShortInfoPage(
        title = stringResource(R.string.charge_giveaway),
        imageResId = R.drawable.giveaway_charge,
        descriptionResId = R.string.charge_giveaway_info
    )
}

@Composable
fun InfoPageThree() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        InfoTitle(stringResource(R.string.level_system))
        Image(
            modifier = Modifier.padding(bottom = 56.dp).width(294.dp).height(41.dp),
            painter = painterResource(R.drawable.progress_bar),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 14.dp),
            text = stringResource(R.string.level_system_title),
            style = TextStyles.infoTitle
        )
        Text(text = stringResource(R.string.punctuality), style = TextStyles.infoSubTitle)
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = stringResource(R.string.punctuality_details),
            style = TextStyles.infoText
        )
        Text(text = stringResource(R.string.friendly), style = TextStyles.infoSubTitle)
        Text(
            text = stringResource(R.string.friendly_details),
            style = TextStyles.infoText
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun InfoPageFour() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxHeight()
    ) {
        Spacer(modifier = Modifier.weight(1f))
        InfoTitle(stringResource(R.string.level_system))
        Image(
            modifier = Modifier.padding(bottom = 54.dp).width(294.dp).height(41.dp),
            painter = painterResource(R.drawable.progress_bar_full),
            contentDescription = null
        )
        Text(
            modifier = Modifier.padding(bottom = 14.dp),
            text = stringResource(R.string.level_system_2_details),
            style = TextStyles.infoTitle
        )

        LevelSquares()
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun LevelSquares() {
    Row(Modifier.padding(bottom = 14.dp, top = 14.dp)) {
        LevelColumn(stringResource(R.string.level, 1), stringResource(R.string.priorities), 91.dp)
        LevelColumn(stringResource(R.string.level, 2), stringResource(R.string.trading), 91.dp)
    }

    Row {
        LevelColumn(
            stringResource(R.string.level, 3),
            stringResource(R.string.reserve_in_advance, 2), 135.dp
        )
        LevelColumn(
            stringResource(R.string.level, 4),
            stringResource(R.string.reserve_in_advance, 3),
            135.dp
        )
    }
}

@Composable
fun LevelColumn(level: String, explanation: String, boxHeight: Dp) {
    Column(
        Modifier.padding(end = 13.dp)
            .border(BorderStroke(2.dp, Colors.blackPearl), shape = RectangleShape)
            .size(width = 130.dp, height = boxHeight),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = level, style = TextStyles.levelTitle)
        Text(explanation, style = TextStyles.levelExplanation)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BottomPagerIndicator(
    pagerState: PagerState,
    modifier: Modifier = Modifier,
) {
    val pageCount = pagerState.pageCount
    val currentPage = pagerState.currentPage
    val coroutine = rememberCoroutineScope()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.Center
        ) {
            items(pageCount) { pageIndex ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 11.dp)
                        .width(30.dp)
                        .height(9.dp)
                        .background(
                            color = if (pageIndex == currentPage) Colors.acid else Colors.lightGrey,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable {
                            coroutine.launch {
                                pagerState.scrollToPage(pageIndex)
                            }
                        }
                )
            }
        }
    }
}