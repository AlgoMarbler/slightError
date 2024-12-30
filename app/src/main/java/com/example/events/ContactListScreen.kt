package com.example.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContactListScreen(viewState : ContactListViewStateBinding) {
    when (viewState) {
        is ContactListViewStateBinding.Content -> {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(
                        rememberScrollState()
                    )
            ) {
                viewState.contact.forEachIndexed{index, item ->
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .clickable {
                                viewState.onClick(index)
                            }
                            .padding(horizontal = 20.dp, vertical = 32.dp)
                    ){
                        Text(
                            text = item.name,
                            modifier = Modifier.weight(1f),
                            fontSize = 32.sp,
                        )
                        Text(text = item.phoneNumber)
                    }

                    if (index != viewState.contact.lastIndex) {
                        HorizontalDivider(modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .background(color = Color(0x60ECCFCF))
                        )
                    }
                }
                Button (onClick = viewState.buttonState.onClick) {
                    Text(text = viewState.buttonState.text)
                }
            }
        }
        is ContactListViewStateBinding.ErrorState -> {
            Column(
                modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = "hi")
                Button(onClick = viewState.buttonState.onClick) {
                    Text(text = "retry")
                }
            }
        }
    }
}

