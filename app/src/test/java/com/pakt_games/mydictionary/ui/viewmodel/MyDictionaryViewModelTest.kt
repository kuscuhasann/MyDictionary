package com.pakt_games.mydictionary.ui.viewmodel

import com.google.common.truth.Truth
import com.pakt_games.mydictionary.base.BaseTest
import com.pakt_games.mydictionary.repo.MyDictionaryRepository
import io.mockk.mockk
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

    override fun afterTest() {}

}