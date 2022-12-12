package com.example.personajesdbz.features.charactersdbz.view

import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.personajesdbz.databinding.ItemCharactersBinding
import com.example.personajesdbz.features.charactersdbz.viewmodel.CharactersDbzViewModel
import com.example.personajesdbz.model.CharactersDbzModel

class CharactersDbzViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCharactersBinding.bind(view)

    fun render(
        charactersDbzModel: CharactersDbzModel,
        onClickListener: (CharactersDbzModel) -> Unit,
        onDeleteListener: (CharactersDbzModel) -> Unit
    ) {
        binding.nameCharacterDbz.text = charactersDbzModel.name
        binding.race.text = charactersDbzModel.race
        Glide.with(binding.ivCharacter).load(charactersDbzModel.photo).into(binding.ivCharacter)
        itemView.setOnClickListener {
            onClickListener(charactersDbzModel)
        }
        binding.btnDelete.setOnClickListener {
            onDeleteListener(charactersDbzModel)
        }
    }
}