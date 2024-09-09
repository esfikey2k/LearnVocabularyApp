package com.example.learnvocabularyapp.service

import com.example.learnvocabularyapp.model.WordsModel
import retrofit2.Call
import retrofit2.http.GET

interface IWordsAPI {
        @GET("4c40ef4eb191daf12f6b")
        fun getData(): Call<List<WordsModel>>
}