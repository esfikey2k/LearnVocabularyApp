package com.example.learnvocabularyapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity("MODEL")
data class WordsModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val wordEn: String,
    val wordEs: String,
    val wordGe: String,
    val wordTr: String,
    val sentenceEn: String,
    val sentenceEs: String,
    val sentenceGe: String,
    val sentenceTr: String,
    val wordImageUrl: String
) : Parcelable