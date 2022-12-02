package com.example.neteasecloudmusicsecondversionapplication.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.musiclibrary.entity.PlayerSong
import com.example.musiclibrary.entity.Song
import com.example.musiclibrary.repository.PlayerListRepository
import com.example.musiclibrary.repository.SearchListRepository
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class SearchResultViewModel : ViewModel() {
    fun getData(word:String):Flow<PagingData<Song>>{
        return SearchListRepository.getData(word).cachedIn(viewModelScope)
    }
    fun saveCurrentSong(song:Song){
        MainScope().launch {
            val arNameList : MutableList<String> = mutableListOf()
            for(j in song.ar.indices){
                arNameList.add(song.ar[j].name)
            }
            val playerSong = PlayerSong(song.name,song.id,arNameList, listOf(song.al.name,song.al.picUrl))
            PlayerListRepository.addElement(playerSong)
        }
    }
}