package com.example.events

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<ContactListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactListScreen(viewModel.viewState.binding)
        }
        handleEvent(viewModel.event)
    }

    private fun handleEvent(eventFlowVar: Flow<EventFlow>) {
        lifecycleScope.launch {
            eventFlowVar.collect{
                when(it){
                    is EventFlow.Call -> startActivity(it.intent)
                }
            }
        }

    }
}



