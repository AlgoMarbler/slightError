package com.example.events

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.ai.client.generativeai.type.content

class ContactListViewState(
    val onClick: (index : Int) -> Unit, // contactItemOnClick
    val onError: () -> Unit, // moveToErrorState
    val onContent: () -> Unit, // moveToContent
) {
    private val content by lazy{
        ContactListViewStateBinding.Content(
            contact = listOf(
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe",
                    phoneNumber = "001-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 2",
                    phoneNumber = "002-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 3",
                    phoneNumber = "003-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 4",
                    phoneNumber = "004-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 5",
                    phoneNumber = "005-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 6",
                    phoneNumber = "006-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 7",
                    phoneNumber = "007-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 8",
                    phoneNumber = "008-456-7890"
                ),
                ContactListViewStateBinding.Content.Contact(
                    name = "John Doe 9",
                    phoneNumber = "009-456-7890"
                ),
            ),
            onClick = { index -> onClick(index)},
            buttonState = ContactListViewStateBinding.ButtonState(
                text = "Error state",
                onClick = onError,
            )
        )
    }


    private var _binding: ContactListViewStateBinding by mutableStateOf(
        content
    )

    fun onTargetView(targetView: TargetView){
        _binding = when (targetView) {
            TargetView.Content -> content
            TargetView.Error -> ContactListViewStateBinding.ErrorState(
                buttonState = ContactListViewStateBinding.ButtonState(
                    text = "text",
                    onClick = onContent,
                )
            )
        }
    }
    val binding: ContactListViewStateBinding
        get() = _binding

    sealed interface TargetView{
        data object Content: TargetView
        data object Error: TargetView
    }
}