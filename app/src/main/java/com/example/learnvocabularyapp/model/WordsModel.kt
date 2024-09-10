package com.example.learnvocabularyapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class WordsModel (
    val id: Int,
    val wordEn: String,
    val wordEs: String,
    val wordTr: String,
    val wordImageUrl: String
) : Parcelable