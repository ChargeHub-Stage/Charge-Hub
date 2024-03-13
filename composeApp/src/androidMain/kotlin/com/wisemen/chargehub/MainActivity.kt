package com.wisemen.chargehub

import App
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import db.chargehub.User
import db.repository.user.RemoteUserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainActivity : ComponentActivity(), KoinComponent {

    init {
        Log.d("TAGG", ":Init mainnn ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepo: RemoteUserRepository by inject()

        userRepo.create(User(id = 1L, levelId = 1L, "Jan", "Jan@piet.com", "eee", 1L))
        setContent {
            App(userRepo.getAll().map { it.name }.first())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    //App()
}