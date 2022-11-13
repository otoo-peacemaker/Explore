package com.peacecodetech.countries.utils

import androidx.recyclerview.widget.DiffUtil
import com.peacecodetech.countries.data.response.CountryDetails

object CharacterDiffUtil : DiffUtil.ItemCallback<CountryDetails>() {
    override fun areItemsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CountryDetails, newItem: CountryDetails): Boolean {
        return oldItem.id == newItem.id
    }
}
