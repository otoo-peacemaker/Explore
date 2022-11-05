package com.peacecodetech.countries.utils

import androidx.recyclerview.widget.DiffUtil
import com.peacecodetech.countries.model.Info

object CharacterDiffUtil : DiffUtil.ItemCallback<Info>() {
    override fun areItemsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Info, newItem: Info): Boolean {
        return oldItem.id == newItem.id
    }
}
