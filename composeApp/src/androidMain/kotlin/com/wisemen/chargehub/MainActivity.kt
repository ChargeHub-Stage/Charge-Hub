package com.wisemen.chargehub

import App
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import db.chargehub.User
import db.repository.user.RemoteUserRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepo: RemoteUserRepository by inject()
        var user: User? = null

        userRepo.findAll().onEach {
            user = it.firstOrNull()
        }

        setContent {
            App(user?.name ?: "Nothing found in db")
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}