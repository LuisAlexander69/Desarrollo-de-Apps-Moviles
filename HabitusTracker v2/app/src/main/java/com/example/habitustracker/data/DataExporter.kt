package com.example.habitustracker.data

import com.example.habitustracker.model.Habit

object DataExporter {

    fun exportToCSV(habits: List<Habit>): String {
        val sb = java.lang.StringBuilder()
        // Header
        sb.append("ID,Nombre,Frecuencia,Categoria,Completado,Racha,UltimaFecha\n")
        
        habits.forEach { habit ->
            sb.append("\"${habit.id}\",")
            sb.append("\"${habit.name.replace("\"", "\"\"")}\",")
            sb.append("\"${habit.frequency.displayName}\",")
            sb.append("\"${habit.category.displayName}\",")
            sb.append("${habit.isCompleted},")
            sb.append("${habit.streakCount},")
            sb.append(habit.lastCompletedTimestamp ?: "")
            sb.append("\n")
        }
        return sb.toString()
    }

    fun exportToJSON(habits: List<Habit>): String {
        val sb = java.lang.StringBuilder()
        sb.append("[\n")
        habits.forEachIndexed { index, habit ->
            sb.append("  {\n")
            sb.append("    \"id\": \"${habit.id}\",\n")
            sb.append("    \"name\": \"${habit.name.replace("\"", "\\\"")}\",\n")
            sb.append("    \"frequency\": \"${habit.frequency.name}\",\n")
            sb.append("    \"category\": \"${habit.category.name}\",\n")
            sb.append("    \"isCompleted\": ${habit.isCompleted},\n")
            sb.append("    \"streakCount\": ${habit.streakCount},\n")
            sb.append("    \"lastCompletedTimestamp\": ${habit.lastCompletedTimestamp}\n")
            if (index < habits.size - 1) {
                sb.append("  },\n")
            } else {
                sb.append("  }\n")
            }
        }
        sb.append("]")
        return sb.toString()
    }
}
