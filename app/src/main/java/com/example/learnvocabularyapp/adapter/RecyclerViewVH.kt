package com.example.learnvocabularyapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.learnvocabularyapp.databinding.ItemWordRecyclerViewBinding
import com.example.learnvocabularyapp.model.WordsModel

class RecyclerViewVH(private val binding: ItemWordRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: WordsModel){
        binding.tvWordNameRecyclerView.text = model.wordTr
    }

}