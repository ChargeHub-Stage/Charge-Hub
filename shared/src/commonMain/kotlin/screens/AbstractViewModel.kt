package screens

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.ViewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Abstract view model which holds the basic functions each view model needs to have.
 *  [A] represents the UiAction.
 *  [E] represents the UiEvent.
 *  [S] represents the UiState which can be a nullable type of Any if no state is applicable.
 */
abstract class AbstractViewModel<A, E, S : Any?> : KMMViewModel() {

    private val eventChannel: Channel<E>
        get() = Channel()

    val eventFlow: Flow<E>
        get() = eventChannel.receiveAsFlow()

    suspend fun sendEvent(event: E) {
        eventChannel.send(event)
    }


    abstract var state: MutableStateFlow<S>
        internal set

    abstract fun onAction(action: A): Job


}