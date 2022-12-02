package com.example.personajesdbz.features.charactersdbz.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.personajesdbz.R
import com.example.personajesdbz.databinding.FragmentCharactersDbzBinding
import com.example.personajesdbz.features.base.DbzFragment
import com.example.personajesdbz.model.CharactersDbzModel
import java.util.UUID

class CharactersDbzFragment : DbzFragment() {

    private lateinit var binding: FragmentCharactersDbzBinding

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
        dbzActivity.charactersDbzViewModel.charactersDbzList.observe(viewLifecycleOwner) {
            initRecyclerView(it)
            binding.charactersDbzContainer.isVisible = true
        }
        dbzActivity.charactersDbzViewModel.charactersDbzDelete.observe(viewLifecycleOwner) {
            binding.charactersDbzContainer.isVisible = true
        }
        dbzActivity.charactersDbzViewModel.charactersDbzError.observe(viewLifecycleOwner) {
            binding.charactersDbzContainer.isVisible = false
        }
        return binding.root
    }


    private fun initRecyclerView(list: ArrayList<CharactersDbzModel>) {
        binding.recyclerCharactersDbz.layoutManager = LinearLayoutManager(context)
        binding.recyclerCharactersDbz.adapter =
            activity?.let { it ->
                CharactersDbzAdapter(it, list) {
                    findNavController().navigate(
                        CharactersDbzFragmentDirections.actionCharactersDbzFragmentToEnterCharactersFragment(
                            (it.id)))
                }
            }
    }

    private fun getCharactersDbz() {
        activity?.applicationContext.let {
            dbzActivity.charactersDbzViewModel.getCharactersDbz()
        }
    }

    override fun onResume() {
        super.onResume()
        getCharactersDbz()
    }
}
