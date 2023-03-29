package com.barryzeha.appci.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.barryzeha.appci.domain.model.QuotesZen


//Las anotaciones solo son necesarias si deseamos un nombre diferente  a los de la dataclass y los campos en la base de datos
@Entity(tableName = "quote_table")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = true) val id:Long=0,
    @ColumnInfo(name="quote")val quote:String="",
    @ColumnInfo(name = "author")val author:String="",
    @ColumnInfo(name = "preFormat")val preFormat:String=""
)
fun QuotesZen.toDatabase()=QuoteEntity(quote=quote,author = author, preFormat = preFormat)
