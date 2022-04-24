package com.pakt_games.mydictionary.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "DICTIONARYWORDS")
data class DictionaryWords (
    @PrimaryKey(autoGenerate = true) var uid: Int,
    @ColumnInfo(name = "key_first_word") val keyFirstWord: String?,
    @ColumnInfo(name = "value_second_word") val valueSecondWord: String?
)