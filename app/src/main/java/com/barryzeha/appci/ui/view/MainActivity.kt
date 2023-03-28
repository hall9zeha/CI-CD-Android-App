package com.barryzeha.appci.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.barryzeha.appci.databinding.ActivityMainBinding
import com.barryzeha.appci.domain.model.QuotesZen
import com.barryzeha.appci.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var bind:ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)
        getQuotes()
        setUpObserver()
    }

    private fun getQuotes(){
        viewModel.getQuotesZen("quotes")
    }
    private fun setUpObserver(){
        viewModel.quotesList.observe(this){
            it.let{list->
                setQuoteInViews(list.random())
            }
        }
    }

    private fun setQuoteInViews(quote:QuotesZen){
        bind.tvQuote.text=quote.quote
        bind.tvAuthor.text=String.format("\"%s\"",quote.author)

    }
}