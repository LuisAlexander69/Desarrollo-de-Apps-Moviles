package com.example.habitustracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.habitustracker.R
import com.example.habitustracker.model.Habit

class HabitAdapter(
    private var habits: List<Habit>,
    private val onHabitToggled: (String) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val checkbox: CheckBox = view.findViewById(R.id.checkboxHabit)
        val tvName: TextView = view.findViewById(R.id.tvHabitName)
        val tvCategory: TextView = view.findViewById(R.id.tvHabitCategory)
        val tvFrequency: TextView = view.findViewById(R.id.tvHabitFrequency)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.tvName.text = habit.name
        holder.tvCategory.text = habit.category.displayName
        holder.tvFrequency.text = habit.frequency.displayName
        
        holder.checkbox.setOnCheckedChangeListener(null)
        holder.checkbox.isChecked = habit.isCompleted
        
        holder.checkbox.setOnCheckedChangeListener { _, _ ->
            onHabitToggled(habit.id)
        }
    }

    override fun getItemCount() = habits.size

    fun updateData(newHabits: List<Habit>) {
        habits = newHabits
        notifyDataSetChanged()
    }
}
