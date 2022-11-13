package com.peacecodetech.countries.data.source

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peacecodetech.countries.network.CountryApiService
import com.peacecodetech.countries.model.Countries
import com.peacecodetech.countries.network.CountryApiResponse
import com.peacecodetech.countries.model.Info
import com.peacecodetech.countries.utils.STARTING_PAGE

// GitHub page API is 1 based: https://developer.github.com/v3/#pagination

class CountryPagingSource(
    private val apiService: CountryApiService
) : PagingSource<Int, Countries>() {

    override val keyReuseSupported: Boolean = true

    override fun getRefreshKey(state: PagingState<Int, Countries>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Countries> {
        val position = if (params.key == null) STARTING_PAGE else params.key

        return try {
            val response: CountryApiResponse = apiService.getAllCountries()
            val info: Info = response.info
            val next: String? = Uri.parse(info.next).getQueryParameter("page")

            val characters: List<Countries> = response.results

            LoadResult.Page(
                data = characters,
                prevKey = if (position == STARTING_PAGE) null else position!! - 1,
                nextKey = next?.toInt()
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}