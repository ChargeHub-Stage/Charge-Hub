package com.wisemen.chargehub.ui.screens.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wisemen.chargehub.R
import com.wisemen.chargehub.ui.screens.LevelSquares
import com.wisemen.chargehub.ui.theme.TextStyles

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
        imageResId = R.drawable.laadbeurten_info,
        descriptionResId = R.string.charges_info
    )
}

@Composable
fun InfoPageTwo() {
    ShortInfoPage(
        title = stringResource(R.string.charge_giveaway),
        imageResId = R.drawable.laadbeurt_afstaan,
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