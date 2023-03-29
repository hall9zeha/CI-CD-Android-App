package com.barryzeha.appci.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryzeha.appci.data.Repository
import com.barryzeha.appci.domain.QuotesInteractor
import com.barryzeha.appci.domain.model.QuotesZen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val usesCase: QuotesInteractor): ViewModel() {

    private var _quote: MutableLiveData<QuotesZen> = MutableLiveData()
    val quote get() = _quote


    fun getQuotesZen(mode:String){
        viewModelScope.launch {
            val quotesList=usesCase.getQuotes(mode)
            if(!quotesList.isNullOrEmpty()){
                val randomIndex= (quotesList.indices).random()
                _quote.postValue(quotesList[randomIndex])
            }
        }
    }
    fun getRandomQuote(){
        viewModelScope.launch {
            val quoteRandom=usesCase.getRandomQuote()
            quoteRandom?.let{
                _quote.postValue(it)
            }
        }
    }
}