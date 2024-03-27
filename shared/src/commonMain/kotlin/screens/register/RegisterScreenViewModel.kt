package screens.register

import ValidationRules
import com.rickclephas.kmm.viewmodel.coroutineScope
import data.CurrentRegisterState
import db.networking.request.CreateUserRequest
import db.repository.car.RemoteCarRepository
import db.repository.user.RemoteUserRepository
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import screens.AbstractViewModel

class RegisterScreenViewModel :
    AbstractViewModel<RegisterScreenUiAction, RegisterScreenUiEvent, RegisterScreenUiState>(
        RegisterScreenUiState()
    ) {

    private val carRepository: RemoteCarRepository by inject()
    private val userRepository: RemoteUserRepository by inject()

    fun getState() : RegisterScreenUiState {
        return state.value
    }

    override fun onAction(action: RegisterScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is RegisterScreenUiAction.OnNextClickedAction -> handleNext()
            is RegisterScreenUiAction.OnPreviousClickedAction -> handlePrevious()
            is RegisterScreenUiAction.OnEmailChangedAction -> {
                handleFieldChange(
                    StateFields.EMAIL,
                    action.email,
                    ValidationRules::isEmailValid
                )
            }

            is RegisterScreenUiAction.OnFirstNameChangedAction -> handleFieldChange(
                StateFields.FIRSTNAME,
                action.firstName,
                ValidationRules::isValidFirstName
            )

            is RegisterScreenUiAction.OnLastNameChangedAction -> handleFieldChange(
                StateFields.LASTNAME,
                action.lastName,
                ValidationRules::isValidLastName
            )

            is RegisterScreenUiAction.OnPasswordChangedAction -> handleFieldChange(
                StateFields.PASSWORD,
                action.password,
                ValidationRules::isValidPassword
            )

            is RegisterScreenUiAction.OnCarIdChangedAction -> handleFieldChange(
                StateFields.VIN,
                action.vin,
                ValidationRules::isValidVIN
            )

            is RegisterScreenUiAction.OnFinaliseRegisterAction -> handleRegisterFinalization()


            is RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> _state.update {
                it.copy(
                    isPrivacyChecked = !state.value.isPrivacyChecked
                )
            }

            RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> _state.update {
                it.copy(
                    isPrivacyChecked = !_state.value.isPrivacyChecked
                )
            }
        }
    }

    private suspend fun handleRegisterFinalization() {
        val request = CreateUserRequest(
            levelId = "1",
            firstName = state.value.firstName,
            lastName = state.value.lastName,
            password = state.value.password,
            email = state.value.email,
            carId = state.value.vin,
            currentPoints = 0L,
        )
        userRepository.create(request)
    }

    private suspend fun handleNext() {
        if (state.value.currentRegisterState == CurrentRegisterState.CAR_CONNECT) {
            carRepository.fetchCarConnectData(state.value.vin)
        }
        _state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.next())
        }
        sendEvent(RegisterScreenUiEvent.OnNextClickedEvent)
    }


    private suspend fun handlePrevious() {
        _state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.previous())
        }
        sendEvent(RegisterScreenUiEvent.OnPreviousClickedEvent)
    }

    private fun handleFieldChange(
        field: StateFields,
        value: String,
        validationRule: (String) -> Boolean
    ) {
        _state.update {
            when (field) {
                StateFields.EMAIL -> it.copy(email = value, isEmailValid = validationRule(value))
                StateFields.FIRSTNAME -> it.copy(
                    firstName = value,
                    isFirstNameValid = validationRule(value)
                )

                StateFields.LASTNAME -> it.copy(
                    lastName = value,
                    isLastNameValid = validationRule(value)
                )

                StateFields.PASSWORD -> it.copy(
                    password = value,
                    isPasswordValid = validationRule(value)
                )

                StateFields.VIN -> it.copy(vin = value, isVinValid = validationRule(value))
            }
        }
    }

    private enum class StateFields {
        EMAIL,
        FIRSTNAME,
        LASTNAME,
        PASSWORD,
        VIN
    }
}