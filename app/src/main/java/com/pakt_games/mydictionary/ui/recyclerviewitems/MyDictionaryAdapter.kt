package com.pakt_games.mydictionary.ui.recyclerviewitems

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pakt_games.mydictionary.R
import com.pakt_games.mydictionary.db.DictionaryWords

class MyDictionaryAdapter(private val myDictionarList: List<DictionaryWords>) :RecyclerView.Adapter<MyDictionarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDictionarViewHolder {

        return MyDictionarViewHolder(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.recylerview_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyDictionarViewHolder, position: Int) {
        val word = this.myDictionarList[position]
        holder.bind(word)
    }

    override fun getItemCount():Int  {
        println("SÝZE"+myDictionarList.size)
        return this.myDictionarList.size
    }
}