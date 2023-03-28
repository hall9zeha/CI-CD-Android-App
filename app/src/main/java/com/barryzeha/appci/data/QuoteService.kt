package com.barryzeha.appci.data

import com.barryzeha.appci.domain.model.QuotesZen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:ApiClient) {
    suspend fun getQuotesZen(mode:String):List<QuotesZen>{
        return withContext(Dispatchers.IO){
            val response=api.getQuotes(mode)
            response.body()?: emptyList()
        }
    }
}