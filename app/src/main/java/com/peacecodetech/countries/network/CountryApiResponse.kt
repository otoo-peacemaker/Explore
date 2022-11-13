

package com.peacecodetech.countries.network

import com.peacecodetech.countries.model.Countries
import com.peacecodetech.countries.model.Info

 class CountryApiResponse (
    val info: Info,
    val results: List<Countries>
)
