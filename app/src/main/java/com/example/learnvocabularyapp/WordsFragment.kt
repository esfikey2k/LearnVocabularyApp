package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.learnvocabularyapp.adapter.RecyclerViewAdapter
import com.example.learnvocabularyapp.constants.Constants
import com.example.learnvocabularyapp.databinding.FragmentWordsBinding
import com.example.learnvocabularyapp.model.WordsModel
import com.example.learnvocabularyapp.service.IWordsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random


class WordsFragment : Fragment() {

    private var _binding: FragmentWordsBinding? = null
    private val binding get() = _binding!!

    private var wordsModel: ArrayList<WordsModel>?= null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWordsBinding.inflate(inflater, container, false)
        val view = binding.root

        try {
            val language= arguments?.getString("language")
            loadData(language.toString())
        }catch (e: Exception){
            e.printStackTrace()
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(language: String){
        
        val retrofit= Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service= retrofit.create(IWordsAPI::class.java)

        val call= service.getData()

        call.enqueue(object : Callback<List<WordsModel>> {
            override fun onResponse(p0: Call<List<WordsModel>>, p1: Response<List<WordsModel>>) {
                if (p1.isSuccessful){
                    p1.body()?.let {
                        wordsModel= ArrayList(it)

                        binding.rvWordList.layoutManager= GridLayoutManager(requireContext(),2)
                        val wordAdapter= RecyclerViewAdapter(wordsModel!!){model ->

                            val bundle= Bundle().apply {
                                putString("tr", model.wordTr)
                                putString("en", model.wordEn)
                                putString("es", model.wordEs)
                                putString("ge", model.wordGe)
                                putString("sentenceEs", model.sentenceEs)
                                putString("sentenceEn", model.sentenceEn)
                                putString("sentenceTr", model.sentenceTr)
                                putString("sentenceGe", model.sentenceGe)
                                putString("wordImageUrl", model.wordImageUrl)
                                putInt("id", model.id)
                                putString("language", language)
                            }

                            view?.findNavController()?.navigate(R.id.action_wordsFragment2_to_detailFragment,bundle)

                        }

                        binding.rvWordList.adapter= wordAdapter
                        wordAdapter.notifyDataSetChanged()

                        swipeRefresh(wordsModel!!,wordAdapter)

                    }
                }
            }

            override fun onFailure(p0: Call<List<WordsModel>>, p1: Throwable) {
                p1.printStackTrace()
            }

        })

    }

    private fun swipeRefresh(wordsList: ArrayList<WordsModel>, adapter: RecyclerViewAdapter){
        binding.swiperefresh.setOnRefreshListener {
            wordsList.shuffle()
            wordsList.shuffle(Random(System.currentTimeMillis()))
            adapter.notifyDataSetChanged()
            binding.swiperefresh.isRefreshing = false
        }
    }

}