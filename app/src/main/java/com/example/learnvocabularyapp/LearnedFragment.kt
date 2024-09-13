package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnvocabularyapp.adapter.RecyclerViewAdapter
import com.example.learnvocabularyapp.databinding.FragmentLearnedBinding
import com.example.learnvocabularyapp.model.WordsModel
import com.example.learnvocabularyapp.room.ModelDB
import com.example.learnvocabularyapp.room.WordsDAO


class LearnedFragment : Fragment() {

    private var _binding: FragmentLearnedBinding? = null
    private val binding get() = _binding!!

    private lateinit var wordsDao: WordsDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding= FragmentLearnedBinding.inflate(inflater,container,false)
        val view= binding.root


        val wordsDb = ModelDB.getInstance(requireContext())
        wordsDao= wordsDb.wordsDao


        val list= wordsDao.getAll() as ArrayList<WordsModel>

        binding.recyclerViewFragmentLearned.layoutManager= GridLayoutManager(requireContext(),2)
        val adapter= RecyclerViewAdapter(list){model ->
            setFragmentResult("learnedWord", bundleOf(
                "learnedId" to model.id,
                "learnedEn" to model.wordEn,
                "learnedEs" to model.wordEs,
                "learnedGe" to model.wordGe,
                "learnedTr" to model.wordTr,
                "learnedSentenceEn" to model.sentenceEn,
                "learnedSentenceEs" to model.sentenceEs,
                "learnedSentenceGe" to model.sentenceGe,
                "learnedSentenceTr" to model.sentenceTr,
                "learnedWordImageUrl" to model.wordImageUrl
            ))

            view.findNavController().navigate(R.id.action_learnedFragment2_to_detailFragment)

        }
        binding.recyclerViewFragmentLearned.adapter= adapter


        return view
    }




}