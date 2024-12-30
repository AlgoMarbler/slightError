package com.example.events

sealed interface ContactListViewStateBinding {
    data class Content(
        val contact: List<Contact>,
        val onClick: (index: Int) -> Unit,
        val buttonState: ButtonState,
    ) : ContactListViewStateBinding {
        data class Contact(
            val name: String,
            val phoneNumber: String,
        )
    }

    data class ErrorState(
        val buttonState: ButtonState,
    ) : ContactListViewStateBinding
    data class ButtonState(
        val text: String,
        val onClick: () -> Unit,
    )

}