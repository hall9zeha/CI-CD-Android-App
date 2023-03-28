package com.barryzeha.appci.data

import com.barryzeha.appci.domain.model.QuotesZen

interface Repository {
    suspend fun getQuotes(mode:String):List<QuotesZen>
}