package com.barryzeha.appci.common

import com.barryzeha.appci.data.database.entities.QuoteEntity
import com.barryzeha.appci.domain.model.QuotesZen

fun QuotesZen.toDomain() = QuotesZen(quote=quote,author=author,preFormat=preFormat)
fun QuoteEntity.toDomain() = QuotesZen(quote=quote, author = author, preFormat = preFormat)