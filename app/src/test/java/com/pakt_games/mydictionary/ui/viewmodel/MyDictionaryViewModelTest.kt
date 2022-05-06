package com.pakt_games.mydictionary.ui.viewmodel

import com.google.common.truth.Truth
import com.pakt_games.mydictionary.base.BaseTest
import com.pakt_games.mydictionary.db.DictionaryWords
import com.pakt_games.mydictionary.repo.MyDictionaryRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

class MyDictionaryViewModelTest: BaseTest() {

    private lateinit var viewModel: MyDictionaryViewModel

    //Mockk
    private val repository: MyDictionaryRepository = mockk(relaxed = true)

    override fun beforeTest() {
        viewModel = MyDictionaryViewModel(repository)
    }

    @ExperimentalTime
    @Test
    fun verify_viewModel_isInitialized() {
        Truth.assertThat(this::viewModel.isInitialized).isTrue()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalTime
    @Test
    fun getWordsDataInSQL_readAllDataDB_isNotNull() = runBlockingTest  {
        //given
        coEvery { repository.getAllSavedWordsData() } returns (listOf(DictionaryWords(1, "toy", "oyuncak"), DictionaryWords(2, "zaman", "time")))
        //when
        viewModel.getWordsDataInSQL()
        //then
        Truth.assertThat(viewModel.readAllDataDB.value).isNotNull()
    }

    @ExperimentalCoroutinesApi
    @ExperimentalTime
    @Test
    fun insertWordsToDB_addToDb_success() = runBlockingTest  {
        //given
        val dictionaryObject = DictionaryWords(1,"toy", "oyuncak")
        //when
        viewModel.insertWordsToDB(dictionaryObject)
        coEvery { repository.insertWordsAsync(dictionaryObject) } returns Unit
        val result = repository.insertWordsAsync(dictionaryObject)
        //then
        Truth.assertThat(result).isNotNull()
    }

    override fun afterTest() {}

}