package com.example.neteasecloudmusicsecondversionapplication.vm

import androidx.lifecycle.*
import com.example.musiclibrary.entity.HotKey
import com.example.musiclibrary.entity.HotKeyJson
import com.example.musiclibrary.repository.HotKeyRepository
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SearchViewModel : ViewModel() {
    private val _hotSongList = MutableLiveData<Boolean>()
    val hotKeyLiveData = Transformations.switchMap(_hotSongList) {
        HotKeyRepository.getList()
    }
    fun requestHotKet(isRequest: Boolean) {
        _hotSongList.value = isRequest
    }


}
