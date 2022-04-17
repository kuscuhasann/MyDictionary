package com.pakt_games.mydictionary.di

import com.pakt_games.mydictionary.ui.viewmodel.MyDictionaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myDictionaryViewModel = module {
    viewModel { MyDictionaryViewModel() }
}