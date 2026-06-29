package com.example.habitustracker.data

import com.example.habitustracker.model.Category
import com.example.habitustracker.model.Frequency
import com.example.habitustracker.model.Habit
import com.example.habitustracker.model.Achievement
import java.util.UUID

object HabitRepository {
    private val habits = mutableListOf<Habit>()
    private val achievements = mutableListOf<Achievement>()

    init {
        // Datos de ejemplo con rachas iniciales
        habits.add(Habit(UUID.randomUUID().toString(), "Beber agua", Frequency.DAILY, Category.FOOD, false, 3))
        habits.add(Habit(UUID.randomUUID().toString(), "Leer 10 páginas", Frequency.DAILY, Category.READING, true, 5, System.currentTimeMillis()))
        habits.add(Habit(UUID.randomUUID().toString(), "Salir a correr", Frequency.WEEKLY, Category.EXERCISE, false, 1))

        // Logros de ejemplo
        achievements.add(Achievement("ach_first", "Primer Paso", "Completa tu primer hábito", false, "COMPLETED_COUNT", 1))
        achievements.add(Achievement("ach_streak_3", "Racha de Bronce", "Consigue una racha de 3 días en cualquier hábito", false, "STREAK", 3))
        achievements.add(Achievement("ach_streak_5", "Racha de Plata", "Consigue una racha de 5 días en cualquier hábito", false, "STREAK", 5))
        achievements.add(Achievement("ach_creator", "Planificador", "Crea al menos 4 hábitos en tu lista", false, "HABIT_COUNT", 4))
        
        checkAchievements()
    }

    fun getHabits(): List<Habit> {
        return habits.toList()
    }

    fun getAchievements(): List<Achievement> {
        return achievements.toList()
    }

    fun addHabit(name: String, frequency: Frequency, category: Category) {
        habits.add(Habit(UUID.randomUUID().toString(), name, frequency, category, false, 0))
        checkAchievements()
    }

    fun toggleHabitCompletion(habitId: String) {
        val habit = habits.find { it.id == habitId }
        if (habit != null) {
            habit.isCompleted = !habit.isCompleted
            if (habit.isCompleted) {
                habit.streakCount += 1
                habit.lastCompletedTimestamp = System.currentTimeMillis()
            } else {
                if (habit.streakCount > 0) {
                    habit.streakCount -= 1
                }
                habit.lastCompletedTimestamp = null
            }
            checkAchievements()
        }
    }
    
    fun getProgressStats(): Pair<Int, Int> {
        val completed = habits.count { it.isCompleted }
        val total = habits.size
        val percentage = if (total > 0) (completed * 100) / total else 0
        return Pair(completed, percentage)
    }

    fun checkAchievements() {
        val completedCount = habits.count { it.isCompleted }
        val maxStreak = habits.maxOfOrNull { it.streakCount } ?: 0
        val totalHabits = habits.size

        achievements.forEach { achievement ->
            if (!achievement.isUnlocked) {
                when (achievement.conditionType) {
                    "COMPLETED_COUNT" -> {
                        if (completedCount >= achievement.threshold) {
                            achievement.isUnlocked = true
                        }
                    }
                    "STREAK" -> {
                        if (maxStreak >= achievement.threshold) {
                            achievement.isUnlocked = true
                        }
                    }
                    "HABIT_COUNT" -> {
                        if (totalHabits >= achievement.threshold) {
                            achievement.isUnlocked = true
                        }
                    }
                }
            }
        }
    }
}
