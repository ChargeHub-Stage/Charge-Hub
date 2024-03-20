package db.repository.networkcalls

import db.database.networkcalls.CarConnectNetworkCallsDatabase
import db.database.networkcalls.CarConnectNetworkCallsWrapper
import db.repository.car.RemoteCarRepository
import networking.CarConnectService
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging

class RemoteCarConnectNetworkCallsRepository : KoinComponent {

    private val carConnectService: CarConnectService by inject()

    private val database: CarConnectNetworkCallsDatabase
        get() = inject<CarConnectNetworkCallsWrapper>().value.database

    private val carRepository: RemoteCarRepository by inject()

    /**
     * Will either make a network call to the car connect service.
     * Or fetch data locally from the database if no call is allowed.
     */
    suspend fun fetchCarData(vin: String) {
        if (database.createOrUpdateNetworkCall()) {
            carConnectService.getCarInfo(vin).also {
                if (carRepository.fetchById(vin) == null) {
                    carRepository.create(it.toCreateCarRequest())
                } else carRepository.update(vin, it.toCreateCarRequest())
            }
        }
    }

}