package com.example.habitustracker

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitustracker.adapter.HabitAdapter
import com.example.habitustracker.data.HabitRepository
import com.example.habitustracker.model.Category
import com.example.habitustracker.model.Frequency
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabitAdapter
    private lateinit var rvHabits: RecyclerView
    private lateinit var pbProgress: ProgressBar
    private lateinit var tvProgress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHabits = findViewById(R.id.rvHabits)
        pbProgress = findViewById(R.id.pbProgress)
        tvProgress = findViewById(R.id.tvProgress)

        adapter = HabitAdapter(HabitRepository.getHabits()) { habitId ->
            HabitRepository.toggleHabitCompletion(habitId)
            updateDashboard()
            adapter.updateData(HabitRepository.getHabits())
        }
        
        rvHabits.layoutManager = LinearLayoutManager(this)
        rvHabits.adapter = adapter

        updateDashboard()

        findViewById<FloatingActionButton>(R.id.fabAddHabit).setOnClickListener {
            showAddHabitDialog()
        }
    }

    private fun updateDashboard() {
        val (completed, percentage) = HabitRepository.getProgressStats()
        pbProgress.progress = percentage
        tvProgress.text = "Progreso: $percentage% ($completed completados)"
    }

    private fun showAddHabitDialog() {
        // En un proyecto real se inflaría dialog_add_habit.xml y se tomarían los datos
        // Aquí simulamos añadir uno nuevo para simplificar
        HabitRepository.addHabit("Nuevo Hábito", Frequency.DAILY, Category.OTHER)
        adapter.updateData(HabitRepository.getHabits())
        updateDashboard()
    }
}
