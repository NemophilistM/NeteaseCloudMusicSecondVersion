package com.example.musiclibrary.pagingsource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.musiclibrary.REQUEST_PAGE_MAX
import com.example.musiclibrary.entity.Song
import com.example.musiclibrary.source.SearchListNetDatasource

class SearchSongPagingSource(private val datasource: SearchListNetDatasource,private val word:String) : PagingSource<Int, Song>() {
    override fun getRefreshKey(state: PagingState<Int, Song>): Int? =null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Song> {
        return try {
            //获取传入页码
            val currentPage = params.key ?: 1
            val prevKey = if (currentPage > 1) currentPage - 1 else null
            // 向数据源层请求数据
            val response = datasource.getListFromNet(word, REQUEST_PAGE_MAX,(currentPage - 1) * REQUEST_PAGE_MAX)
            val maxPage = if (response.result.songCount % 20 != 0) {
                response.result.songCount / 20 + 1
            } else {
                response.result.songCount / 20
            }
            val nextPage = if (currentPage < maxPage) {
                currentPage + 1
            } else {
                //没有更多数据
                null
            }
            LoadResult.Page(
                response.result.songs,
                prevKey,
                nextPage
            )

        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}