package com.pakt_games.mydictionary.di

import androidx.room.Room
import com.pakt_games.mydictionary.db.DictionaryWordDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "WORDSDB"
val dbModule = module {

    // Room Database
    single { Room.databaseBuilder(androidContext(), DictionaryWordDatabase::class.java, DATABASE_NAME).build() }

    // WordsDao
    single { get<DictionaryWordDatabase>().dictionaryWordsDao() }
}