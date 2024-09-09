package com.example.learnvocabularyapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.learnvocabularyapp.adapter.RecyclerViewAdapter
import com.example.learnvocabularyapp.constants.Constants
import com.example.learnvocabularyapp.databinding.FragmentWordsBinding
import com.example.learnvocabularyapp.model.WordsModel
import com.example.learnvocabularyapp.service.IWordsAPI
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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


//            view.findNavController().navigate(R.id.action_wordsFragment2_to_detailFragment)


        loadData()




        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun loadData(){
        
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
                        println(wordsModel.toString())

                        binding.rvWordList.layoutManager= LinearLayoutManager(requireContext())
                        val wordAdapter= RecyclerViewAdapter(wordsModel!!)
                        binding.rvWordList.adapter= wordAdapter


                    }
                }
            }

            override fun onFailure(p0: Call<List<WordsModel>>, p1: Throwable) {
                p1.printStackTrace()
            }

        })


    }



}