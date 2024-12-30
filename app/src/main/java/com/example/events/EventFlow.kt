package com.example.events

import android.content.Intent

sealed interface EventFlow {

    data class Call(val intent: Intent) : EventFlow
}