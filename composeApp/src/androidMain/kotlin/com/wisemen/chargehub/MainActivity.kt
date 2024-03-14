package com.wisemen.chargehub

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import db.chargehub.Car
import db.networking.request.CreateChargeHubRequest
import db.repository.car.RemoteCarRepository
import db.repository.chargehub.RemoteChargeHubRepository
import db.repository.user.RemoteUserRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepo: RemoteUserRepository by inject()
        val carRepo: RemoteCarRepository by inject()
        val chargeHubRepo: RemoteChargeHubRepository by inject()

        lifecycleScope.launch {
            val cars = mutableListOf<Car>()

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