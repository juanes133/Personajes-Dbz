package com.example.personajesdbz.features.entercharacters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personajesdbz.databinding.FragmentEnterCharactersBinding
import com.example.personajesdbz.features.base.DbzFragment
import com.example.personajesdbz.model.CharactersDbzModel

class EnterCharactersFragment : DbzFragment() {

    private lateinit var binding: FragmentEnterCharactersBinding
    private var character: CharactersDbzModel? = null
    private val args: EnterCharactersFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEnterCharactersBinding.inflate(inflater, container, false)
        character =
            dbzActivity.charactersDbzViewModel.charactersDbzList.value?.firstOrNull { x -> x.id == args.idcharacter }
        character?.let {
            binding.character.setText(it.name)
            binding.race.setText(it.race)
            binding.photo.setText(it.photo)
        }

        binding.btnSave.setOnClickListener {
            if (character == null) {
                character = CharactersDbzModel(it.id.toString(),
                    binding.character.text.toString(),
                    binding.race.text.toString(),
                    binding.photo.text.toString())
            }
            character?.let {
                character = CharactersDbzModel(it.id,
                    binding.character.text.toString(),
                    binding.race.text.toString(),
                    binding.photo.text.toString())
                insertCharactersDbz()
                findNavController().navigateUp()
            }
        }
        dbzActivity.charactersDbzViewModel.charactersDbzInsert.observe(viewLifecycleOwner) {
        }
        dbzActivity.charactersDbzViewModel.charactersDbzError.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            //TODO: que pasa si hay error?
        }
        return binding.root
    }

    private fun insertCharactersDbz() {
        context?.let {
            character?.let { character ->
                dbzActivity.charactersDbzViewModel.insertCharactersDbz(character)
            }
        }
    }
}