

package com.peacecodetech.countries.network

import com.peacecodetech.countries.model.*

class CountryApiResponse (
    val altSpellings: List<String>,
    val area: Double,
    val borders: List<String>,
    val capital: List<String>,
    val capitalInfo: CapitalInfo,
    val car: Car,
    val coatOfArms: CoatOfArms,
    val continents: List<String>,
    val currencies: Currencies,
    val flag: String,
    val flags: Flags,
    val gini: Gini,
    val idd: Idd,
    val independent: Boolean,
    val landlocked: Boolean,
    val languages: Languages,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val region: String,
    val startOfWeek: String,
    val status: String,
    val subregion: String,
    val timezones: List<String>,
    val tld: List<String>,
    val translations: Translations,
    val unMember: Boolean
)