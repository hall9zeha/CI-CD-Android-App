package com.barryzeha.appci.domain

import com.barryzeha.appci.common.Constants
import com.barryzeha.appci.data.RepositoryImpl
import com.barryzeha.appci.data.database.entities.QuoteEntity
import com.barryzeha.appci.domain.model.QuotesZen
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * Project AppCI
 * Created by Barry Zea H. on 30/03/23.
 * Copyright (c) Barry Zea H. All rights reserved.
 *
 */
class QuotesInteractorTest{
    @RelaxedMockK
    private lateinit var repository:RepositoryImpl

    lateinit var quotesInteractor: QuotesInteractor

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quotesInteractor = QuotesInteractor(repository)
    }

    //usaremos "runBlocking" si tenemos que ejecutar funciones que usen corrutinas
    @Test
    fun getDatabaseQuotesIfApiNotReturnAnything()= runBlocking {

        val listQuotes= arrayListOf(QuotesZen("hello world","Barry","<>"))
        //Given

        //1- simulamos un llamada a la api que devuelve una lista vacía, por error de conexion o cualquier otra eventualidad
        coEvery { repository.getApiQuotes(Constants.QUOTES_MODE) } returns emptyList()

        //when
        //2- eso deberá de suceder cuando se llame a la función en el interactor
        quotesInteractor.getQuotes(Constants.QUOTES_MODE)

        //then
        //3- se verificará que efectivamente al obtener una lista vacía desde la api, se llama a la base de datos en el repositorio
        //exactly=1 es una comprobación para que la función o método sea llamada al menos una vez
        coVerify(exactly = 1) { repository.getDatabaseQuotes() }
        }

    //Ahora probaremos el caso en que la API devuelva una lista de valores
    //entonces se guardará en la base de datos y devolverá la respuesta desde la api, no desde la base de datos
    @Test
    fun whenApiReturnAQuotesList()= runBlocking{
        val quotesList= listOf(QuotesZen("hello world","Barry","<>"))
        val quotesEntity= listOf(QuoteEntity(0,"hello world","Barry","<>"))
        //Given

        coEvery { repository.getApiQuotes(Constants.QUOTES_MODE) }returns  quotesList

        //when
        val response= quotesInteractor.getQuotes(Constants.QUOTES_MODE)

        //then
        //comprobamos que al obtener respuesta de la api se cumplan los pasos dentro de la función
        //1 - limpiar la tabla de quotes
        //2 - guardar la nueva lista traida de la API
        coVerify (exactly = 1){repository.clearQuotesOfDatabase()  }
        coVerify (exactly = 1){repository.saveQuotesToDatabase(quotesEntity)  }//repository.saveQuotesToDatabase(any())si no queremos simular los parámetros que recibe la función
        //exactly=0 significa que esta función no se llamará, como se espera
        coVerify (exactly = 0){repository.getDatabaseQuotes() }

        //la respuesta debe ser igual a nuestra quoteList simulada
        assert(response==quotesList)
    }


    @Test
    fun whenGetRandomQuoteReturnSomething()= runBlocking {
        //creamos una lista para simular la devolución al llamar a la base de datos
        val quoteList= listOf(QuotesZen(quote="Hello world", author = "Barry", preFormat = "<h1>hello World<h1>"))
        //given
        //1- retornamos quoteList al llamar a la base de datos
        coEvery { repository.getDatabaseQuotes() } returns quoteList

        //when
        //2- cargamos la respuesta desde el interactor que usará el repositorio en el paso (1)
        val response= quotesInteractor.getRandomQuote()
        //3- comprobamos que nos devuelva un valor dentro de la lista, en este caso será el primer elemento
        //la función real deberá devolver cualquier elemento dentro de la lista.
        //Para fines prácticos solo traemos el primer elemento
        assert(response == quoteList.first())


    }

    @Test
    fun whenGetRandomQuotesReturnNull()= runBlocking{

        //Given
        //cargamos una lista vacía al llamar desde el repositorio a la base de datos
        coEvery { repository.getDatabaseQuotes() } returns emptyList()

        //When
        //Cuando al llamar a la base de datos retorne una lista vacía o null
        val response = quotesInteractor.getRandomQuote()

        //comparamos que efectivamente la respuesta sea null
        assert(response == null)
    }
}