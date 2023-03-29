package com.barryzeha.appci.domain

import com.barryzeha.appci.data.Repository
import com.barryzeha.appci.data.database.entities.toDatabase
import com.barryzeha.appci.domain.model.QuotesZen

class QuotesInteractor (private val repository: Repository){

    suspend fun getQuotes(mode:String):List<QuotesZen>{
        val quotes=repository.getApiQuotes(mode)
        return if(!quotes.isNullOrEmpty()){
            repository.clearQuotesOfDatabase()
            repository.saveQuotesToDatabase(quotes.map { it.toDatabase() })
            quotes
        }else{
            repository.getDatabaseQuotes()
        }
    }
    suspend fun getRandomQuote():QuotesZen?{
        val quotes= repository.getDatabaseQuotes()
        if(!quotes.isNullOrEmpty()){
            return quotes[(quotes.indices).random()]
        }
        return null
    }
}