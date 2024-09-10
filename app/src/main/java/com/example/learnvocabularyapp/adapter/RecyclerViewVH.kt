package com.example.learnvocabularyapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.learnvocabularyapp.databinding.ItemWordRecyclerViewBinding
import com.example.learnvocabularyapp.model.WordsModel
import com.squareup.picasso.Picasso

class RecyclerViewVH(private val binding: ItemWordRecyclerViewBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(model: WordsModel, onClickListener: (WordsModel) -> Unit){
        binding.tvWord.text = model.wordTr
        Picasso.get().load(model.wordImageUrl).into(binding.itemImage)

        binding.cardviewRecycle.setOnClickListener {
            onClickListener.invoke(model)
        }


    }

}