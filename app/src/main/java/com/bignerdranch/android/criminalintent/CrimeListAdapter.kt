package com.bignerdranch.android.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.criminalintent.databinding.ListItemCrimeBinding
import com.bignerdranch.android.criminalintent.databinding.ListItemSeriousCrimeBinding

class CrimeHolder(
    private val binding: ListItemCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}

class SeriousCrimeHolder(
    private val binding: ListItemSeriousCrimeBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            Toast.makeText(
                binding.root.context,
                "Serious ${crime.title} clicked!",
                Toast.LENGTH_SHORT
            ).show() }
    }
}

class CrimeListAdapter(
    private val crimes: List<Crime>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() { // Use generic ViewHolder

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_SERIOUS = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (crimes[position].requiresPolice) VIEW_TYPE_SERIOUS else VIEW_TYPE_NORMAL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_SERIOUS -> {
                val binding = ListItemSeriousCrimeBinding.inflate(inflater, parent, false)
                SeriousCrimeHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is SeriousCrimeHolder -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size
}
