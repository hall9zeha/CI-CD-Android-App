package com.barryzeha.appci.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.barryzeha.appci.data.database.entities.QuoteEntity


@Database(entities = [QuoteEntity::class], version = 1)
abstract class QuoteZenDatabase:RoomDatabase() {
    abstract fun getQuotesDao():QuoteZenDAO
}