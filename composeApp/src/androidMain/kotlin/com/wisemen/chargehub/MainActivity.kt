package com.wisemen.chargehub

import App
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import db.chargehub.Car
import db.networking.request.CreateReservationRequest
import db.repository.car.RemoteCarRepository
import db.repository.chargehub.RemoteChargeHubRepository
import db.repository.level.RemoteLevelRepository
import db.repository.reservation.RemoteReservationRepository
import db.repository.user.RemoteUserRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime

class MainActivity : ComponentActivity(), KoinComponent {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepo: RemoteUserRepository by inject()
        val carRepo: RemoteCarRepository by inject()
        val chargeHubRepo: RemoteChargeHubRepository by inject()
        val levelRepo: RemoteLevelRepository by inject()
        val reservationRepo: RemoteReservationRepository by inject()

        lifecycleScope.launch {
            val cars = mutableListOf<Car>()

            reservationRepo.create(
                CreateReservationRequest(
                    "1",
                    "1",
                    "1",
                    LocalDateTime.now().toString(),
                    LocalDateTime.now().plusHours(2).toString()
                )
            )

            carRepo.findAll().collect {
                cars += it
            }

            setContent {
                App(cars.map { it.brand }.toString())
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}