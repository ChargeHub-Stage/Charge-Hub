package com.wisemen.chargehub.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.wisemen.chargehub.nav.ChargeHubNavGraph
import com.wisemen.chargehub.ui.theme.AppTheme
import db.repository.FirebaseRepository

@Preview
@Composable
fun HomeScreenPreview() {
    AppTheme {
        HomeLayout()
    }
}

@Composable
@Destination
@ChargeHubNavGraph
fun HomeScreen() {
    HomeLayout()
}

@Composable
fun HomeLayout() {
    Text(text = FirebaseRepository().getCurrentUserUid())
}