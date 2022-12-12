package com.example.personajesdbz.features.charactersdbz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personajesdbz.CharactersDbzApplication
import com.example.personajesdbz.databinding.FragmentCharactersDbzBinding
import com.example.personajesdbz.features.base.DbzFragment
import com.example.personajesdbz.features.charactersdbz.viewmodel.CharactersDbzViewModel
import com.example.personajesdbz.features.charactersdbz.viewmodel.CharactersDbzViewModelFactory
import com.example.personajesdbz.model.CharactersDbzModel
import java.util.UUID

class CharactersDbzFragment : DbzFragment() {

    private lateinit var binding: FragmentCharactersDbzBinding
    private var adapter: CharactersDbzAdapter? = null
    private val charactersDbzViewModel: CharactersDbzViewModel by viewModels {
        CharactersDbzViewModelFactory(
            (activity?.application as CharactersDbzApplication).charactersDbzRepository,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCharactersDbzBinding.inflate(inflater, container, false)
        binding.btnEnter.setOnClickListener {
            findNavController().navigate(
                CharactersDbzFragmentDirections.actionCharactersDbzFragmentToEnterCharactersFragment(
                    (UUID.randomUUID().toString())))
        }
        charactersDbzViewModel.charactersDbzList.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { list ->
                initRecyclerView(list)
                binding.charactersDbzContainer.isVisible = true
            }
        }
        charactersDbzViewModel.charactersDbzDeleted.observe(viewLifecycleOwner) {
            it.getContentIfNotHandled()?.let { character ->
                binding.charactersDbzContainer.isVisible = true
                adapter?.remove(character)
            }
        }
        charactersDbzViewModel.charactersDbzError.observe(viewLifecycleOwner) {
            binding.charactersDbzContainer.isVisible = false
        }
        charactersDbzViewModel.getCharactersDbz()
        return binding.root
    }

    private fun initRecyclerView(list: ArrayList<CharactersDbzModel>) {
        binding.recyclerCharactersDbz.layoutManager = LinearLayoutManager(context)
        adapter = CharactersDbzAdapter(list, {
            findNavController().navigate(
                CharactersDbzFragmentDirections.actionCharactersDbzFragmentToEnterCharactersFragment(
                    (it.id)))
        }, {
            charactersDbzViewModel.deleteCharactersDbz(it)
        })
        binding.recyclerCharactersDbz.adapter = adapter

    }

}
