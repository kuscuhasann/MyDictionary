package com.pakt_games.mydictionary.di

import com.pakt_games.mydictionary.repo.MyDictionaryRepository
import org.koin.dsl.module

val myDictionaryRepositoryModule = module {
    factory { MyDictionaryRepository(get()) }
}