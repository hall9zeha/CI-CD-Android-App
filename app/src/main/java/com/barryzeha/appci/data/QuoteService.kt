package com.barryzeha.appci.data

import android.util.Log
import com.barryzeha.appci.domain.model.QuotesZen
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuoteService @Inject constructor(private val api:ApiClient) {
    suspend fun getQuotesZen(mode:String):List<QuotesZen>{
        return withContext(Dispatchers.IO){
            //controlamos la excepción si no hay conexion a internet
            try {
                val response = api.getQuotes(mode)
                response.body()
                //response.body?: emptyList()
            }
            catch(e:Exception){
                Log.e("TAG", e.message.toString() )
            }
            arrayListOf()
        }
    }
}