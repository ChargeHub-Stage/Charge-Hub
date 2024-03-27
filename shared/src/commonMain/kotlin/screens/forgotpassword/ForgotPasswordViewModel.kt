package screens.forgotpassword

import com.rickclephas.kmm.viewmodel.coroutineScope
import db.repository.FirebaseRepository
import kotlinx.coroutines.launch
import screens.AbstractViewModel

class ForgotPasswordViewModel(private val firebaseRepo: FirebaseRepository) :
    AbstractViewModel<ForgotPasswordUiAction, ForgotPasswordUiEvent, ForgotPasswordUiState>(ForgotPasswordUiState()) {

        fun setEmail(email: String) {
            _state.value = state.value.copy(email = email)
        }
        fun getState(): ForgotPasswordUiState {
            return state.value
        }
        override fun onAction(action: ForgotPasswordUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is ForgotPasswordUiAction.OnClickedBackAction -> sendEvent(ForgotPasswordUiEvent.ClickedBackEvent)
            is ForgotPasswordUiAction.OnEmailChangedAction -> _state.value =
                state.value.copy(email = action.email)
            is ForgotPasswordUiAction.OnClickedSendEmailAction -> {
                firebaseRepo.sendForgotPasswordEmail(email = state.value.email) {
                    trySend(ForgotPasswordUiEvent.ClickedSendEmailEvent)
                }
            }
        }
    }
}