package com.pakt_games.mydictionary.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DictionaryWords::class], version = 1)
abstract class DictionaryWordDatabase : RoomDatabase() {

    abstract fun dictionaryWordsDao() : DictionaryWordsDao

}