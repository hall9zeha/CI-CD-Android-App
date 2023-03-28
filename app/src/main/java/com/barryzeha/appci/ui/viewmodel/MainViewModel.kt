package com.barryzeha.appci.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.barryzeha.appci.data.Repository
import com.barryzeha.appci.domain.model.QuotesZen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private var _quotesList: MutableLiveData<List<QuotesZen>> = MutableLiveData()
    val quotesList get() = _quotesList


    fun getQuotesZen(mode:String){
        viewModelScope.launch {
            _quotesList.postValue(repository.getQuotes(mode))
        }
    }
}