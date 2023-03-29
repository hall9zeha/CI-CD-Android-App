package com.barryzeha.appci.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.barryzeha.appci.data.database.entities.QuoteEntity


@Dao
interface QuoteZenDAO {

    @Query("select * from quote_table")
    suspend fun getAllQuotes():List<QuoteEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quotesList:List<QuoteEntity>)
    @Query("delete from quote_table")
    suspend fun deleteAllQuotes()
}