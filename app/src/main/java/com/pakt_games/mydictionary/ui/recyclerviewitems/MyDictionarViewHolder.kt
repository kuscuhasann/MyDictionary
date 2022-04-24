package com.pakt_games.mydictionary.ui.recyclerviewitems

import androidx.recyclerview.widget.RecyclerView
import com.pakt_games.mydictionary.databinding.RecylerviewItemBinding
import com.pakt_games.mydictionary.db.DictionaryWords

class MyDictionarViewHolder(private val binding: RecylerviewItemBinding) :RecyclerView.ViewHolder(binding.root) {

    fun bind(item: DictionaryWords) {
        binding.model = item
        binding.executePendingBindings()
    }
}