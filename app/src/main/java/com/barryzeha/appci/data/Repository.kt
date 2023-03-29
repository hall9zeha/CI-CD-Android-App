package com.barryzeha.appci.data

import com.barryzeha.appci.data.database.entities.QuoteEntity
import com.barryzeha.appci.domain.model.QuotesZen

interface Repository {
    suspend fun getApiQuotes(mode:String):List<QuotesZen>
    suspend fun getDatabaseQuotes():List<QuotesZen>
    suspend fun saveQuotesToDatabase(quotes:List<QuoteEntity>)
    suspend fun clearQuotesOfDatabase()
}