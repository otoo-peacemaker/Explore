

package com.peacecodetech.countries.network

import com.peacecodetech.countries.model.Countries
import com.peacecodetech.countries.model.Info

/**
 * SearchResult from a search, which contains List<Country> holding query data,
 * and a String of network error state.
 */
sealed class CountryApiResponse (
    val info: Info,
    val results: List<Countries>
)
