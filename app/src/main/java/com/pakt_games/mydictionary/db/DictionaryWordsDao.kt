package com.pakt_games.mydictionary.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DictionaryWordsDao {

    @Query("SELECT * FROM dictionarywords")
     suspend fun getAll(): List<DictionaryWords>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertDictionaryWords(dictionaryWords: DictionaryWords)

}