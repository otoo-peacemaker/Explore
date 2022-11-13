package com.peacecodetech.countries.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.android.codelabs.paging.api.CountryApiService
import com.peacecodetech.countries.api.CountrySearchResponse
import com.peacecodetech.countries.utils.STARTING_PAGE

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination
const val GITHUB_STARTING_PAGE_INDEX = 1

class RickMortyDataSource(
    private val apiService: CountryApiService
) : PagingSource<Int, Character>() {

    override val keyReuseSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = if (params.key == null) STARTING_PAGE else params.key

        try {
            val response: CountrySearchResponse = apiService.getAllCharacters()
            val info: Info = response.info
            val next: String? = Uri.parse(info.next).getQueryParameter("page")

            val characters: List<Character> = response.results

            return LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_PAGE) null else position!! - 1,
                nextKey = next?.toInt()
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}