package br.com.iuri.compose.navigation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class NavManager : ViewModel() {
    private val _command = MutableSharedFlow<NavAction>()
    val command = _command.asSharedFlow()

    fun start() {
        viewModelScope
    }

    fun navigate(action: NavAction) {
        viewModelScope.launch {
            _command.emit(action)
        }
    }
}
