package org.wcm.domain.api

import org.wcm.domain.model.InternationalReserve
import java.time.LocalDate

interface InternationalReserveAdapter {

    fun getById(id: Long): InternationalReserve
    fun getByCountryCode(countryCode: String): List<InternationalReserve>
    fun getAllCountriesByDate(date: LocalDate): List<InternationalReserve>
    fun saveAll(reserves: List<InternationalReserve>)
}