package com.example.neteasecloudmusicsecondversionapplication.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musiclibrary.repository.HotKeyRepository
import com.example.musiclibrary.repository.PlayerListRepository
import com.example.musiclibrary.repository.PlayerRepository
import kotlinx.coroutines.launch

class PlayerViewModel :ViewModel() {
    suspend fun requestCount():Int?{
      return PlayerListRepository.getCount()
    }
}