package com.example.marvelhub.presentation.DeatilsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.marvelhub.R
import com.example.marvelhub.databinding.CharacterDetailsFragmentBinding
import com.example.marvelhub.utils.setImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private val characterDetailsFragmentArgs :CharacterDetailsFragmentArgs by navArgs()

    private val viewModel: CharacterDetailsViewModel by viewModels()
    private lateinit var binding :CharacterDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = CharacterDetailsFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacterData(characterDetailsFragmentArgs.charcterId)

    }

    override fun onStart() {
        super.onStart()
        subscribeOnliveData()
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }
    private fun subscribeOnliveData(){
        viewModel.character.observe(viewLifecycleOwner, Observer { character->
            binding.characterName.text = character.name
            binding.characterImg.setImage(character.imagePath)
            binding.characterDescription.text = character.description
        })
    }



}