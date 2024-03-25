package screens.landing

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.launch
import screens.BaseComposeViewModel

class LandingScreenViewModel : KMMViewModel(),
    BaseComposeViewModel<LandingScreenUiAction, LandingScreenUiEvent, Nothing?> {

    // No state involved on this page
    override var state: Nothing? = null

    override fun onAction(action: LandingScreenUiAction) = viewModelScope.coroutineScope.launch  {
        when (action) {
            is LandingScreenUiAction.ClickedLoginButtonAction -> sendEvent(LandingScreenUiEvent.ClickedLoginButtonEvent)
            is LandingScreenUiAction.ClickedRegisterButtonAction -> sendEvent(LandingScreenUiEvent.ClickedRegisterButtonEvent)
        }
    }

}