package com.example.habitustracker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.habitustracker.adapter.HabitAdapter
import com.example.habitustracker.data.DataExporter
import com.example.habitustracker.data.HabitRepository
import com.example.habitustracker.firebase.FirebaseSyncManager
import com.example.habitustracker.model.Category
import com.example.habitustracker.model.Frequency
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HabitAdapter
    private lateinit var rvHabits: RecyclerView
    private lateinit var pbProgress: ProgressBar
    private lateinit var tvProgress: TextView
    private lateinit var tvAchievements: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvHabits = findViewById(R.id.rvHabits)
        pbProgress = findViewById(R.id.pbProgress)
        tvProgress = findViewById(R.id.tvProgress)
        tvAchievements = findViewById(R.id.tvAchievements)

        // Configurar RecyclerView
        adapter = HabitAdapter(HabitRepository.getHabits()) { habitId ->
            HabitRepository.toggleHabitCompletion(habitId)
            updateDashboard()
            adapter.updateData(HabitRepository.getHabits())
        }
        
        rvHabits.layoutManager = LinearLayoutManager(this)
        rvHabits.adapter = adapter

        // Botones del Dashboard
        findViewById<Button>(R.id.btnExportCsv).setOnClickListener {
            exportData("csv")
        }

        findViewById<Button>(R.id.btnExportJson).setOnClickListener {
            exportData("json")
        }

        findViewById<Button>(R.id.btnSyncCloud).setOnClickListener {
            FirebaseSyncManager.backupToCloud(this)
        }

        updateDashboard()

        findViewById<FloatingActionButton>(R.id.fabAddHabit).setOnClickListener {
            showAddHabitDialog()
        }
    }

    private fun updateDashboard() {
        val (completed, percentage) = HabitRepository.getProgressStats()
        pbProgress.progress = percentage
        tvProgress.text = "Progreso: $percentage% ($completed completados)"

        // Mostrar logros
        val achievements = HabitRepository.getAchievements()
        val unlockedCount = achievements.count { it.isUnlocked }
        tvAchievements.text = "Logros: $unlockedCount/${achievements.size} desbloqueados"
    }

    private fun exportData(format: String) {
        val habits = HabitRepository.getHabits()
        val content = if (format == "csv") {
            DataExporter.exportToCSV(habits)
        } else {
            DataExporter.exportToJSON(habits)
        }

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Exportación de Hábitos ($format)")
            putExtra(Intent.EXTRA_TEXT, content)
        }
        startActivity(Intent.createChooser(shareIntent, "Compartir archivo de hábitos ($format)"))
    }

    private fun showAddHabitDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_habit, null)
        val etName = dialogView.findViewById<TextInputEditText>(R.id.etHabitName)
        val spinnerFreq = dialogView.findViewById<Spinner>(R.id.spinnerFrequency)
        val spinnerCat = dialogView.findViewById<Spinner>(R.id.spinnerCategory)

        // Llenar Spinner de Frecuencias
        val frequencies = Frequency.values()
        val freqNames = frequencies.map { it.displayName }
        val freqAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, freqNames)
        freqAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFreq.adapter = freqAdapter

        // Llenar Spinner de Categorías
        val categories = Category.values()
        val catNames = categories.map { it.displayName }
        val catAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, catNames)
        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCat.adapter = catAdapter

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Agregar") { dialog, _ ->
                val name = etName.text.toString().trim()
                if (name.isNotEmpty()) {
                    val selectedFreq = frequencies[spinnerFreq.selectedItemPosition]
                    val selectedCat = categories[spinnerCat.selectedItemPosition]
                    
                    HabitRepository.addHabit(name, selectedFreq, selectedCat)
                    adapter.updateData(HabitRepository.getHabits())
                    updateDashboard()
                    Toast.makeText(this, "Hábito agregado con éxito", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "El nombre no puede estar vacío", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
