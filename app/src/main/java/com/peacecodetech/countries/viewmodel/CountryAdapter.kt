package com.peacecodetech.countries.viewmodel

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peacecodetech.countries.databinding.CountryListBinding
import com.peacecodetech.countries.model.Countries
import com.peacecodetech.countries.utils.CharacterDiffUtil

class CountryAdapter :
    PagingDataAdapter<Countries, CountryAdapter.CharacterViewHolder>(CharacterDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CountryListBinding.inflate(LayoutInflater.from(parent.context))
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character: Countries? = getItem(position)
        if (character != null) {
            holder.bind(character)
        }
    }

    class CharacterViewHolder(private val binding: CountryListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(countries: Countries) {
            binding.country.text = countries.name
            binding.city.text = countries.name
        }
    }
}