package com.example.model

data class LoadingState(
    val onStart: () -> Unit,
    val onComplete: () -> Unit,
    val onError: (String?) -> Unit
)
