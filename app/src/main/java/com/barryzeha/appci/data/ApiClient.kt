package com.barryzeha.appci.data

import com.barryzeha.appci.domain.model.QuotesZen
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiClient {
    @GET("{mode}")
    suspend fun getQuotes(@Path("mode") mode:String): Response<List<QuotesZen>>

}