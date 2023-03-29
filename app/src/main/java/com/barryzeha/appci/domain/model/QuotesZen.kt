package com.barryzeha.appci.domain.model

import com.google.gson.annotations.SerializedName

data class QuotesZen(
                     @SerializedName("q")val quote:String="",
                     @SerializedName("a")val author:String="",
                     @SerializedName("h")val preFormat:String="")
