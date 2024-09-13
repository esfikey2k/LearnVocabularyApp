package com.example.learnvocabularyapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.learnvocabularyapp.model.WordsModel

@Dao
interface WordsDAO {

    @Query("SELECT * FROM MODEL")
    fun getAll(): List<WordsModel>


    @Query("SELECT * FROM MODEL WHERE id = :id")
    fun getById(id: Int): List<WordsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(model: WordsModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArrayList(model: ArrayList<WordsModel>)

    @Delete
    fun delete(model: WordsModel)

    @Update
    fun update(model: WordsModel)

}