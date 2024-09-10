package com.example.learnvocabularyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.learnvocabularyapp.WordsFragment
import com.example.learnvocabularyapp.databinding.ItemWordRecyclerViewBinding
import com.example.learnvocabularyapp.model.WordsModel

class RecyclerViewAdapter(private val wordsList: ArrayList<WordsModel>, private val onClickListener: (WordsModel) -> Unit): RecyclerView.Adapter<RecyclerViewVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewVH {
        val binding= ItemWordRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerViewVH(binding)
    }

    override fun getItemCount(): Int = wordsList.size

    override fun onBindViewHolder(holder: RecyclerViewVH, position: Int) {
        holder.bind(wordsList[position],onClickListener)

    }
}