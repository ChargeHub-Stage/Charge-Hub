package screens.landing

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.coroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import screens.IViewModel
import screens.login.LoginScreenUiEvent

class LandingScreenViewModel : KMMViewModel(),
    IViewModel<LandingScreenUiAction, LandingScreenUiEvent, Nothing?> {
        private val eventChannel = Channel<LandingScreenUiEvent>()
    val eventFlow = eventChannel.receiveAsFlow()

    // No state involved on this page
    override var state: Nothing? = null

    override fun onAction(action: LandingScreenUiAction) = viewModelScope.coroutineScope.launch  {
        when (action) {
            is LandingScreenUiAction.ClickedLoginButtonAction -> eventChannel.send(LandingScreenUiEvent.ClickedLoginButtonEvent)
            is LandingScreenUiAction.ClickedRegisterButtonAction -> eventChannel.send(LandingScreenUiEvent.ClickedRegisterButtonEvent)
        }
    }

}