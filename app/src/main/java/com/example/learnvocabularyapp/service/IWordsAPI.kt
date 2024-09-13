package com.example.learnvocabularyapp.service

import com.example.learnvocabularyapp.model.WordsModel
import retrofit2.Call
import retrofit2.http.GET

interface IWordsAPI {
        @GET("f30f67152e7e372c51e4")
        fun getData(): Call<List<WordsModel>>
}