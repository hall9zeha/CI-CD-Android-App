package com.barryzeha.appci.data

import com.barryzeha.appci.common.toDomain
import com.barryzeha.appci.domain.model.QuotesZen
import javax.inject.Inject

class RepositoryImpl (private val api:QuoteService) :Repository{
    override suspend fun getQuotes(mode: String): List<QuotesZen> {
       val response = api.getQuotesZen(mode)
        return response.map { it.toDomain()}
    }
}