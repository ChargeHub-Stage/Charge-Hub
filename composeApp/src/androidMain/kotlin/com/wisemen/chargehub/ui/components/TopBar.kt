package com.wisemen.chargehub.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.wisemen.chargehub.ui.theme.Colors
import com.wisemen.chargehub.ui.theme.TextStyles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    centerText: String,
    onAction: () -> Unit,
    body: @Composable ((PaddingValues) -> Unit)
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = centerText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = TextStyles.topBarTitle,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onAction() }) {
                        Row {
                            Icon(
                                imageVector = Icons.Filled.ChevronLeft,
                                contentDescription = null
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Colors.white),
            )
        },
    ) { contentPadding ->
        body(contentPadding)
    }
}