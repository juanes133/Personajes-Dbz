package com.example.personajesdbz.features.charactersdbz.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.personajesdbz.R
import com.example.personajesdbz.model.CharactersDbzModel

class CharactersDbzAdapter(
    private val charactersDbzList: ArrayList<CharactersDbzModel>,
    private val onClickListener: (CharactersDbzModel) -> Unit,
    private val onDeleteListener: (CharactersDbzModel) -> Unit,
) :
    RecyclerView.Adapter<CharactersDbzViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersDbzViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CharactersDbzViewHolder(
            layoutInflater.inflate(
                R.layout.item_characters,
                parent,
                false))
    }

    override fun onBindViewHolder(holder: CharactersDbzViewHolder, position: Int) {
        holder.render(charactersDbzList[position], onClickListener, onDeleteListener)
    }

    fun remove(charactersDbzModel: CharactersDbzModel) {
        val index = charactersDbzList.indexOf(charactersDbzModel)
        charactersDbzList.removeAt(index)
        notifyItemRemoved(index)
        notifyItemRangeChanged(index, charactersDbzList.size)
    }

    override fun getItemCount(): Int = charactersDbzList.size

}