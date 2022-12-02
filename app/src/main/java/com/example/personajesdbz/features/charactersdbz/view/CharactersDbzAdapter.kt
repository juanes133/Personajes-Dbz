package com.example.personajesdbz.features.charactersdbz.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personajesdbz.model.CharactersDbzModel
import com.example.personajesdbz.R

class CharactersDbzAdapter(
    val context: Context,
    private val charactersDbzList: List<CharactersDbzModel>,
    private val onClickListener: (CharactersDbzModel) -> Unit
) :
    RecyclerView.Adapter<CharactersDbzViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDbzViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersDbzViewHolder(
            layoutInflater.inflate(
                R.layout.item_characters,
                parent,
                false), context)
    }

    override fun onBindViewHolder(holder: CharactersDbzViewHolder, position: Int) {
        holder.render(charactersDbzList[position], onClickListener)
    }

    override fun getItemCount(): Int = charactersDbzList.size
}