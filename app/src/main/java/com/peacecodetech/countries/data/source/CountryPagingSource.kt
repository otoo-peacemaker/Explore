package com.peacecodetech.countries.data.source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.peacecodetech.countries.model.Info
import com.peacecodetech.countries.network.CountryApiResponse
import com.peacecodetech.countries.network.CountryApiService
import com.peacecodetech.countries.utils.LOAD_DELAY_MILLIS
import com.peacecodetech.countries.utils.STARTING_PAGE
import kotlinx.coroutines.delay


class CountryPagingSource(
    private val apiService: CountryApiService
) : PagingSource<Int, Info>() {

    override val keyReuseSupported: Boolean = true


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Info> {
        val start = params.key ?: STARTING_PAGE
        // Load as many items as hinted by params.loadSize
        val range = start.until(start + params.loadSize)//load size

        if (start != STARTING_PAGE) delay(LOAD_DELAY_MILLIS) //a little delay to simulate the load

        return try {
            val response: CountryApiResponse = apiService.getAllCountries()
            Log.i("PagingSource","$response")
            LoadResult.Page(
                data = range.map {
                    Info(
                        id = it,
                        flag = response.flag,
                        countryName = response.name.common,
                        city = response.name.official
                    )
                },

                // Make sure we don't try to load items behind the STARTING_KEY
                prevKey = when (start) {
                    STARTING_PAGE -> null
                    else -> ensureValidKey(key = range.first - params.loadSize)
                },
                nextKey = range.last + 1// support loading infinite items
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Info>): Int? {
        val anchorPosition =
            state.anchorPosition ?: return null//load most recently accessed index in the list
        val article = state.closestItemToPosition(anchorPosition)
            ?: return null//load closest item to the last accessed index in the list
        return ensureValidKey(key = article.id?.minus((state.config.pageSize / 2)) ?: 1)
    }

    /**
     * Makes sure the paging key is never less than [STARTING_PAGE]
     */
    private fun ensureValidKey(key: Int) = Integer.max(STARTING_PAGE, key)
}