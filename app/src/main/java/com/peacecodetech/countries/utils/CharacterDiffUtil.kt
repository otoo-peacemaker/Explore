package com.peacecodetech.countries.utils

import androidx.recyclerview.widget.DiffUtil
import com.peacecodetech.countries.model.Countries

object CharacterDiffUtil : DiffUtil.ItemCallback<Countries>() {
    override fun areItemsTheSame(oldItem: Countries, newItem: Countries): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Countries, newItem: Countries): Boolean {
        return oldItem.id == newItem.id
    }
}
