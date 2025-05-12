package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.InternationalReserveAdapter
import org.wcm.usecase.api.InternationalReserveApi
import java.time.LocalDate

@Component
class InternationalReserveUseCase(
    private val adapter: InternationalReserveAdapter
): InternationalReserveApi {

    override fun getBiId(id: Long) =
        adapter.getById(id)

    override fun getByCountryCode(countryCode: String) =
        adapter.getByCountryCode(countryCode)

    override fun getAllCountriesByDate(date: LocalDate) =
        adapter.getAllCountriesByDate(date)
}