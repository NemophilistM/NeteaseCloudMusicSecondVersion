package com.example.neteasecloudmusicsecondversionapplication.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.musiclibrary.entity.SearchSong
import com.example.musiclibrary.entity.Song
import com.example.musiclibrary.repository.SearchListRepository
import kotlinx.coroutines.flow.Flow

class SearchResultViewModel : ViewModel() {
    fun getData(word:String):Flow<PagingData<Song>>{
        return SearchListRepository.getData(word).cachedIn(viewModelScope)
    }
}