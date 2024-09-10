package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import com.example.learnvocabularyapp.databinding.FragmentChoiceBinding


class ChoiceFragment : Fragment() {

    private var _binding: FragmentChoiceBinding? = null
    private val binding get() = _binding!!

    private lateinit var language: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChoiceBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.ivEngland.setOnClickListener {
            language= "en"
            val bundle= bundleOf("language" to language)
            view.findNavController().navigate(R.id.action_choiceFragment_to_wordsFragment2,bundle)

        }

        binding.ivSpain.setOnClickListener {
            language= "es"
            val bundle= bundleOf("language" to language)
            view.findNavController().navigate(R.id.action_choiceFragment_to_wordsFragment2,bundle)
        }

        return view
    }


}