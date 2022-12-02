package com.example.personajesdbz.features.charactersdbz.view

import android.content.Context
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.personajesdbz.DbzActivity
import com.example.personajesdbz.databinding.ItemCharactersBinding
import com.example.personajesdbz.model.CharactersDbzModel

class CharactersDbzViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCharactersBinding.bind(view)

    fun render(
        charactersDbzModel: CharactersDbzModel,
        onClickListener: (CharactersDbzModel) -> Unit
    ) {
        binding.nameCharacterDbz.text = charactersDbzModel.name
        binding.race.text = charactersDbzModel.race
        Glide.with(binding.ivCharacter).load(charactersDbzModel.photo).into(binding.ivCharacter)
        itemView.setOnClickListener {
            onClickListener(charactersDbzModel)
        }
        binding.btnDelete.setOnClickListener {
            (context as DbzActivity).charactersDbzViewModel.deleteCharactersDbz(charactersDbzModel.id)
            binding.itemCharacter.isVisible = false
        }
    }
}