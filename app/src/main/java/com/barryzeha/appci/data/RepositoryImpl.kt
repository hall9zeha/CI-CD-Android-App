package com.barryzeha.appci.data

import com.barryzeha.appci.common.toDomain
import com.barryzeha.appci.data.database.QuoteZenDAO
import com.barryzeha.appci.data.database.entities.QuoteEntity
import com.barryzeha.appci.domain.model.QuotesZen


class RepositoryImpl (private val api:QuoteService, private val db:QuoteZenDAO ) :Repository{
    override suspend fun getApiQuotes(mode: String): List<QuotesZen> {
       val response = api.getQuotesZen(mode)
        return response.map { it.toDomain()}
    }

    override suspend fun getDatabaseQuotes(): List<QuotesZen> {
        val response:List<QuoteEntity> = db.getAllQuotes()
        return response.map { it.toDomain() }
    }

    override suspend fun saveQuotesToDatabase(quotes: List<QuoteEntity>) {
        db.insertQuotes(quotes)
    }

    override suspend fun clearQuotesOfDatabase() {
        db.deleteAllQuotes()
    }
}