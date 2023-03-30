package com.barryzeha.appci.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.barryzeha.appci.common.Constants
import com.barryzeha.appci.domain.QuotesInteractor
import com.barryzeha.appci.domain.model.QuotesZen
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Project AppCI
 * Created by Barry Zea H. on 30/03/23.
 * Copyright (c) Barry Zea H. All rights reserved.
 *
 */
//Para quitar las advertencias al usar Dispatchers.setMain y resetMain
@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest{
    @RelaxedMockK
    private lateinit var interactor: QuotesInteractor

    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    var rule:InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(interactor)
        //Para poder emular las corrutinas dentro del viewModel
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }
    //podemos usar la 'when ViewModel Is Created First Time And Get All Quotes And Set Random Value' como nombre de la función
    //las comillas simples son necesarias para los espacios.
    //eg: fun 'when ViewModel Is Created First Time And Get All Quotes And Set Random Value'(){}
    @Test
    fun whenViewModelIsCreatedFirstTimeAndGetAllQuotesAndSetRandomValue()= runTest{
        val quotesList= listOf<QuotesZen>(QuotesZen("Hello world", "Barry", "<h1>Hello world</h1>"))
        //1 - simulamos el retorno de una lista de elementos al llamar a la API
        //Given
        coEvery { interactor.getQuotes(Constants.QUOTES_MODE) } returns quotesList

        //2 - Cuando  desde el viewModel se llame a la API
        //When
        mainViewModel.getQuotesZen(Constants.QUOTES_MODE)

        //comparamos que el liveData<QuoteZen> quote, contenga un elemento, como esperamos
        //Then
        assert(mainViewModel.quote.value==quotesList.first())

    }

    //Cuando la función getRandomQuote() retorne un null el liveData debera conservar el último valor
    @Test
    fun whenGetRandomQuoteReturnNullKeepLastValue()= runTest{
        //Given
        //Emulamos el último valor, insertandolo en el elemento LiveData "quote"
        val quote=QuotesZen("Work Hard","Martha","<>")
        mainViewModel.quote.value=quote
        coEvery {  interactor.getRandomQuote()} returns null

        //Cuando se llame a la función en el viewModel
        //When
        mainViewModel.getRandomQuote()

        //Comparamos que se mantenga el último valor en la variable LiveData quote
        //Then
        assert(mainViewModel.quote.value==quote)

    }

    //Cuando la función getRandomQuote() devuelva un objeto quote
    @Test
    fun whenGetRandomReturnAnyQuote()= runTest{
        //Given
        val quote=QuotesZen("Work Hard","Martha","<>")
        coEvery { interactor.getRandomQuote() } returns quote

        //When
        mainViewModel.getRandomQuote()

        //Comparamos que el LiveData quote contenga un elemento de tipo Quote()
        //Then
        assert(mainViewModel.quote.value == quote)
    }


}