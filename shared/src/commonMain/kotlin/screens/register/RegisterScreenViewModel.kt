package screens.register

import ValidationRules
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import db.repository.car.RemoteCarRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import screens.AbstractViewModel

class RegisterScreenViewModel :
    AbstractViewModel<RegisterScreenUiAction, RegisterScreenUiEvent, RegisterScreenUiState>() {


    private val carRepository: RemoteCarRepository by inject()

    override var state: MutableStateFlow<RegisterScreenUiState> =
        MutableStateFlow(viewModelScope, RegisterScreenUiState())


    override fun onAction(action: RegisterScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is RegisterScreenUiAction.OnNextClickedAction -> handleNext()
            is RegisterScreenUiAction.OnPreviousClickedAction -> handlePrevious()
            is RegisterScreenUiAction.OnEmailChangedAction -> handleFieldChange(StateFields.EMAIL,action.email, ValidationRules::isEmailValid)
            is RegisterScreenUiAction.OnFirstNameChangedAction -> handleFieldChange(StateFields.FIRSTNAME, action.firstName, ValidationRules::isValidFirstName)
            is RegisterScreenUiAction.OnLastNameChangedAction -> handleFieldChange(StateFields.LASTNAME, action.lastName, ValidationRules::isValidLastName)
            is RegisterScreenUiAction.OnPasswordChangedAction -> handleFieldChange(StateFields.PASSWORD, action.password, ValidationRules::isValidPassword)
            is RegisterScreenUiAction.OnCarIdChangedAction -> handleFieldChange(StateFields.VIN, action.vin, ValidationRules::isValidVIN)

            is RegisterScreenUiAction.OnFinaliseRegisterAction -> { /*TODO */
            }

            is RegisterScreenUiAction.OnPrivacyCheckedChangedAction -> state.update {
                it.copy(
                    isPrivacyChecked = !state.value.isPrivacyChecked
                )
            }

        }
    }

    private suspend fun handleNext() {
        if (state.value.currentRegisterState == CurrentRegisterState.CAR_CONNECT) {
            carRepository.fetchCarConnectData(state.value.vin)
        }
        state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.next())
        }
        sendEvent(RegisterScreenUiEvent.OnNextClickedEvent)
    }

    private suspend fun handlePrevious() {
        state.update {
            it.copy(currentRegisterState = it.currentRegisterState?.previous())
        }
        sendEvent(RegisterScreenUiEvent.OnPreviousClickedEvent)
    }

    private fun handleFieldChange(
        field: StateFields,
        value: String,
        validationRule: (String) -> Boolean
    ) {
        state.update {
            when (field) {
                StateFields.EMAIL -> it.copy(email = value, isEmailValid = validationRule(value))
                StateFields.FIRSTNAME -> it.copy(firstName = value, isFirstNameValid = validationRule(value))
                StateFields.LASTNAME -> it.copy(lastName = value, isLastNameValid = validationRule(value))
                StateFields.PASSWORD -> it.copy(password = value, isPasswordValid = validationRule(value))
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

/**
 * Enums representing the order of the registering pages.
 * The lowest is the starting point, the highest is the ending point.
 */
enum class CurrentRegisterState(private val order: Int) {
    EMAIL(1),
    PROFILE(2),
    CAR_CONNECT(3),
    NOTIFICATIONS(4),
    INFO(5);

    fun next(): CurrentRegisterState? {
        return entries.find { it.order == order + 1 }
    }

    fun previous(): CurrentRegisterState? {
        return entries.find { it.order == order - 1 }
    }
}
