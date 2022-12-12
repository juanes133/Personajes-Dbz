package com.example.personajesdbz.features.entercharacters.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personajesdbz.CharactersDbzApplication
import com.example.personajesdbz.databinding.FragmentEnterCharactersBinding
import com.example.personajesdbz.features.base.DbzFragment
import com.example.personajesdbz.features.entercharacters.viewmodel.EnterCharactersViewModel
import com.example.personajesdbz.features.entercharacters.viewmodel.EnterCharactersViewModelFactory
import com.example.personajesdbz.model.CharactersDbzModel
import java.util.*

class EnterCharactersFragment : DbzFragment() {

    private lateinit var binding: FragmentEnterCharactersBinding
    private var character: CharactersDbzModel? = null
    private val args: EnterCharactersFragmentArgs by navArgs()
    private val enterCharactersViewModel: EnterCharactersViewModel by viewModels {
        EnterCharactersViewModelFactory(
            (activity?.application as CharactersDbzApplication).charactersDbzRepository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEnterCharactersBinding.inflate(inflater, container, false)
        enterCharactersViewModel.findById(args.idcharacter)
        binding.btnSave.isEnabled = false
        enterCharactersViewModel.charactersDbzList.observe(viewLifecycleOwner) { it ->
            it.getContentIfNotHandled()?.let { characterDB ->
                character = characterDB.firstOrNull()
                character?.let {
                    binding.character.setText(it.name)
                    binding.race.setText(it.race)
                    binding.photo.setText(it.photo)
                }
                binding.btnSave.isEnabled = true
            }
        }
        binding.btnSave.setOnClickListener {
            if (character == null) {
                character = CharactersDbzModel(UUID.randomUUID().toString(),
                    binding.character.text.toString(),
                    binding.race.text.toString(),
                    binding.photo.text.toString())
            } else {
                character?.let { characterDbz ->
                    character = CharactersDbzModel(characterDbz.id,
                        binding.character.text.toString(),
                        binding.race.text.toString(),
                        binding.photo.text.toString())
                }
            }
            insertCharactersDbz()
        }
        enterCharactersViewModel.charactersDbzInsert.observe(viewLifecycleOwner) {
            findNavController().navigateUp()
        }
        enterCharactersViewModel.charactersDbzError.observe(viewLifecycleOwner) {
            Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
            //TODO: que pasa si hay error?
        }
        return binding.root
    }

    private fun insertCharactersDbz() {
        context?.let {
            character?.let { character ->
                enterCharactersViewModel.insertCharactersDbz(character)
            }
        }
    }
}