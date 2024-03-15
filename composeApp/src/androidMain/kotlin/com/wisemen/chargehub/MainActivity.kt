package com.wisemen.chargehub

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import db.chargehub.Car
import db.repository.car.RemoteCarRepository
import db.repository.chargehub.RemoteChargeHubRepository
import db.repository.level.RemoteLevelRepository
import db.repository.reservation.RemoteReservationRepository
import db.repository.user.RemoteUserRepository
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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

        lifecycleScope.launch {
            val cars = mutableListOf<Car>()
            val users = userRepo.fetchAll()

            carRepo.findAll().collect { cars += it }

            setContent {
                App(users.map { it.name }.firstOrNull() ?: "User")
            }
        }
    }
}

//@Preview
//@Composable
//fun AppAndroidPreview() {
//    App()
//}