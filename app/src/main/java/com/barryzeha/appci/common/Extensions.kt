package com.barryzeha.appci.common

import com.barryzeha.appci.domain.model.QuotesZen

fun QuotesZen.toDomain() = QuotesZen(quote=quote,author=author,preFormat=preFormat)