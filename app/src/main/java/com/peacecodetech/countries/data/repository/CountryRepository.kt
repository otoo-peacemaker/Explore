/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.peacecodetech.countries.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.peacecodetech.countries.api.CountryApiService
import com.peacecodetech.countries.model.Countries
import kotlinx.coroutines.flow.Flow


/**
 * Repository class that works with local and remote data sources.
 */

class CountryRepository(private val service: CountryApiService) {

    fun getSearchResultStream(query: String): Flow<PagingData<Countries>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CountryApiService(service, query) }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 50
    }
}
