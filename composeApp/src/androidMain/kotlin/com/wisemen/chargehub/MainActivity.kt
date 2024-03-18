package com.wisemen.chargehub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.wisemen.chargehub.ui.screens.NavGraphs
import com.wisemen.chargehub.ui.theme.AppTheme
import db.repository.car.RemoteCarRepository
import db.repository.chargehub.RemoteChargeHubRepository
import db.repository.level.RemoteLevelRepository
import db.repository.reservation.RemoteReservationRepository
import db.repository.user.RemoteUserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepo: RemoteUserRepository by inject()
        val carRepo: RemoteCarRepository by inject()
        val chargeHubRepo: RemoteChargeHubRepository by inject()
        val levelRepo: RemoteLevelRepository by inject()
        val reservationRepo: RemoteReservationRepository by inject()

        setContent {
            AppTheme {
                val navController = rememberNavController()

                Scaffold { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.background)
                            .padding(paddingValues)
                    ) {
                        DestinationsNavHost(
                            navGraph = NavGraphs.chargeHub,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}