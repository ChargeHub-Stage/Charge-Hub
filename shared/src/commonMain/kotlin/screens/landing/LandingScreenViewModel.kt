package screens.landing

import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.launch
import screens.AbstractViewModel

class LandingScreenViewModel :
    AbstractViewModel<LandingScreenUiAction, LandingScreenUiEvent, Unit>(Unit) {

    override fun onAction(action: LandingScreenUiAction) = viewModelScope.coroutineScope.launch {
        when (action) {
            is LandingScreenUiAction.ClickedLoginButtonAction -> {
                sendEvent(LandingScreenUiEvent.ClickedLoginButtonEvent)
            }
            is LandingScreenUiAction.ClickedRegisterButtonAction -> sendEvent(LandingScreenUiEvent.ClickedRegisterButtonEvent)
        }
    }

}