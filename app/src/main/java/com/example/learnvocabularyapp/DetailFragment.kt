package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.learnvocabularyapp.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private var wordTr: String? = null
    private var wordEn: String? = null
    private var wordEs: String? = null
    private var wordImageUrl: String? = null
    private var wordId: String? = null
    private var wordLanguage: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val view = binding.root

        arguments?.let { bundle ->
            wordTr = bundle.getString("tr")
            wordEn = bundle.getString("en")
            wordEs = bundle.getString("es")
            wordImageUrl = bundle.getString("wordImageUrl")
            wordId = bundle.getString("id")
            wordLanguage = bundle.getString("language")
        }

        println("gelen kelime: $wordTr")
        binding.tvDetail.text= wordTr.toString()



        return view
    }

}