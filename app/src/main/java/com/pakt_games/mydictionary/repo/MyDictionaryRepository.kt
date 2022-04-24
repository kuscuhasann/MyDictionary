package com.pakt_games.mydictionary.repo

import com.pakt_games.mydictionary.db.DictionaryWords
import com.pakt_games.mydictionary.db.DictionaryWordsDao

class MyDictionaryRepository(private val dictionaryWordsDao: DictionaryWordsDao) {

    suspend fun insertWordsAsync(dictionaryWords: DictionaryWords) = dictionaryWordsDao.insertDictionaryWords(dictionaryWords)

    suspend fun getAllSavedWordsData(): List<DictionaryWords> {
        val readAllWords: List<DictionaryWords> = dictionaryWordsDao.getAll()
        return readAllWords
    }
}