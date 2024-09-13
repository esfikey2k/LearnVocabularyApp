package com.example.learnvocabularyapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.learnvocabularyapp.model.WordsModel
import com.example.learnvocabularyapp.room.ModelDB.Companion.INSTANCE

@Database(entities = [WordsModel::class], version = 1)
abstract class ModelDB: RoomDatabase() {

    abstract val wordsDao: WordsDAO

    companion object{
        private var INSTANCE: ModelDB? = null

        fun getInstance(context: Context): ModelDB{

            return INSTANCE ?: Room.databaseBuilder(
                context,
                ModelDB::class.java,
                "model_db"
            ).allowMainThreadQueries().build().also {
                INSTANCE= it
            }


        }
    }

}

