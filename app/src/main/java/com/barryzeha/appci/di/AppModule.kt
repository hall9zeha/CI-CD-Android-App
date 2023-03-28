package com.barryzeha.appci.di

import com.barryzeha.appci.data.QuoteService
import com.barryzeha.appci.data.Repository
import com.barryzeha.appci.data.RepositoryImpl
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
    fun repositoryProvide(quoteService:QuoteService):Repository=RepositoryImpl(quoteService)

}