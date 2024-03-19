package screens.register

sealed class RegisterScreenUiEvent {


    data object OnPreviousClickedEvent : RegisterScreenUiEvent()

    data object OnNextClickedEvent : RegisterScreenUiEvent()

}