package org.wcm.usecase.api

import org.wcm.domain.model.InternationalReserve
import java.time.LocalDate

interface InternationalReserveApi {
    fun getBiId(id: Long): InternationalReserve
    fun getByCountryCode(countryCode: String): List<InternationalReserve>
    fun getAllCountriesByDate(date: LocalDate): List<InternationalReserve>
}