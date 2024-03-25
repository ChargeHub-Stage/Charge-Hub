package com.wisemen.chargehub.nav

import com.ramcosta.composedestinations.annotation.NavGraph
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@NavGraph
annotation class ChargeHubNavGraph(
    val start: Boolean = false
)