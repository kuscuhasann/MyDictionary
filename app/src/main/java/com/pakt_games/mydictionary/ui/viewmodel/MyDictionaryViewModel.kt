package com.pakt_games.mydictionary.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pakt_games.mydictionary.db.DictionaryWords
import com.pakt_games.mydictionary.repo.MyDictionaryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MyDictionaryViewModel(private val myDictionaryRepository: MyDictionaryRepository): ViewModel() {

    var readAllDataDB = MutableStateFlow<List<DictionaryWords>>(listOf())

    fun getWordsDataInSQL() {
        viewModelScope.launch {
            readAllDataDB.value = myDictionaryRepository.getAllSavedWordsData()
        }
    }

    fun insertWordsToDB(dictionaryWords: DictionaryWords) {
        viewModelScope.launch(Dispatchers.IO) {
            myDictionaryRepository.insertWordsAsync(dictionaryWords)
        }
    }
}