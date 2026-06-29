package com.example.habitustracker.model

data class Habit(
    val id: String,
    val name: String,
    val frequency: Frequency,
    val category: Category,
    var isCompleted: Boolean = false,
    var streakCount: Int = 0,
    var lastCompletedTimestamp: Long? = null
)

enum class Frequency(val displayName: String) {
    DAILY("Diaria"),
    WEEKLY("Semanal"),
    MONTHLY("Mensual")
}

enum class Category(val displayName: String) {
    EXERCISE("Ejercicio"),
    READING("Lectura"),
    FOOD("Alimentación"),
    OTHER("Otro")
}
