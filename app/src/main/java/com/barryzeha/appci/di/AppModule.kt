package com.barryzeha.appci.di

import android.app.Application
import androidx.room.Room
import com.barryzeha.appci.common.Constants
import com.barryzeha.appci.data.QuoteService
import com.barryzeha.appci.data.Repository
import com.barryzeha.appci.data.RepositoryImpl
import com.barryzeha.appci.data.database.QuoteZenDAO
import com.barryzeha.appci.data.database.QuoteZenDatabase
import com.barryzeha.appci.domain.QuotesInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton

    fun databaseProvides(app:Application)= Room.databaseBuilder(
        app.applicationContext,
        QuoteZenDatabase::class.java,
        Constants.DATABASE_NAME
    )
        .fallbackToDestructiveMigration()
        .build()
        .getQuotesDao()

    @Provides
    @Singleton
    fun repositoryProvide(quoteService:QuoteService,db:QuoteZenDAO):Repository=RepositoryImpl(quoteService,db)

    @Provides
    @Singleton
    fun usesCasesProvides(repository: Repository) = QuotesInteractor(repository)


}