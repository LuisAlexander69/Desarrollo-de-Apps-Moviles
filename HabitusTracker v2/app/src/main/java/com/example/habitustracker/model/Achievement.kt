package com.example.habitustracker.model

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    var isUnlocked: Boolean = false,
    val conditionType: String, // "STREAK" o "COMPLETED_COUNT"
    val threshold: Int
)
