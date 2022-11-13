

package com.peacecodetech.countries.model

import java.lang.Exception

/**
 * SearchResult from a search, which contains List<Country> holding query data,
 * and a String of network error state.
 */
sealed class CountryResponse (
    val info: Info,
    val results: List<Countries>
)
