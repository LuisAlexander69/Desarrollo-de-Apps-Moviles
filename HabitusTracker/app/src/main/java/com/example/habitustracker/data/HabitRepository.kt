package com.example.habitustracker.data

import com.example.habitustracker.model.Category
import com.example.habitustracker.model.Frequency
import com.example.habitustracker.model.Habit
import java.util.UUID

object HabitRepository {
    private val habits = mutableListOf<Habit>()

    init {
        // Datos de ejemplo
        habits.add(Habit(UUID.randomUUID().toString(), "Beber agua", Frequency.DAILY, Category.FOOD, false))
        habits.add(Habit(UUID.randomUUID().toString(), "Leer 10 páginas", Frequency.DAILY, Category.READING, true))
        habits.add(Habit(UUID.randomUUID().toString(), "Salir a correr", Frequency.WEEKLY, Category.EXERCISE, false))
    }

    fun getHabits(): List<Habit> {
        return habits.toList()
    }

    fun addHabit(name: String, frequency: Frequency, category: Category) {
        habits.add(Habit(UUID.randomUUID().toString(), name, frequency, category, false))
    }

    fun toggleHabitCompletion(habitId: String) {
        val habit = habits.find { it.id == habitId }
        if (habit != null) {
            habit.isCompleted = !habit.isCompleted
        }
    }
    
    fun getProgressStats(): Pair<Int, Int> {
        val completed = habits.count { it.isCompleted }
        val total = habits.size
        val percentage = if (total > 0) (completed * 100) / total else 0
        return Pair(completed, percentage)
    }
}
