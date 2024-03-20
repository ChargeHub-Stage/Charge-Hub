package db.repository.networkcalls

import db.database.networkcalls.CarConnectNetworkCallsDatabase
import db.database.networkcalls.CarConnectNetworkCallsWrapper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RemoteCarConnectNetworkCallsRepository : KoinComponent {

    private val database: CarConnectNetworkCallsDatabase
        get() = inject<CarConnectNetworkCallsWrapper>().value.database


    fun insertOrReplaceNetworkCall() {
        database.createOrInsertNetworkCall()
    }


}