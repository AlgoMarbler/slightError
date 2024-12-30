package com.example.events

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ContactListViewModel : ViewModel() {
    val viewState = ContactListViewState(
        onClick = ::onClick,
        onError = ::onError,
        onContent = ::returnToPage,
    )
    private val _event = MutableSharedFlow<EventFlow>()
    val event : Flow<EventFlow> = _event.asSharedFlow()



    private fun onClick(index : Int) {
        val item = (viewState.binding as ContactListViewStateBinding.Content).contact[index]
        val number = item.phoneNumber

        viewModelScope.launch {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
            _event.emit(EventFlow.Call(intent))
        }


    }

    private fun onError() {
        viewState.onTargetView(ContactListViewState.TargetView.Content)
    }

    private fun returnToPage() {
        viewState.onTargetView(ContactListViewState.TargetView.Error)
    }
}
