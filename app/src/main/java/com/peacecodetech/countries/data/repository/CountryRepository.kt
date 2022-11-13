
package com.peacecodetech.countries.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peacecodetech.countries.network.CountryApiService
import com.peacecodetech.countries.data.source.CountryPagingSource
import com.peacecodetech.countries.model.Countries
import com.peacecodetech.countries.utils.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow


/**
 * Repository class that works with local and remote data sources.
 */

class CountryRepository(private val service: CountryApiService) {

    fun getCountries(): Flow<PagingData<Countries>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CountryPagingSource(service) }
        ).flow
    }

}
