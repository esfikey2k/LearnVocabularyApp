package com.example.learnvocabularyapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class DetailViewModel : ViewModel()  {
    private val _isAddedToLearned = MutableLiveData<Boolean>(false)
    val isAddedToLearned: LiveData<Boolean> get() = _isAddedToLearned

    fun addToLearned() {
        _isAddedToLearned.value = true
    }

    fun removeFromLearned() {
        _isAddedToLearned.value = false
    }

    fun setIsAddedToLearned(isAdded: Boolean) {
        _isAddedToLearned.value = isAdded
    }

}