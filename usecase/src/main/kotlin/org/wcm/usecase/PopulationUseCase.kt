package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.PopulationAdapter
import org.wcm.domain.model.Population
import org.wcm.usecase.api.PopulationApi
import java.time.LocalDate

@Component
class PopulationUseCase(
    private val adapter: PopulationAdapter
) : PopulationApi {

    override fun findAllByCountryCode(countryCode: String): List<Population> {
        return adapter.getByCountryCode(countryCode)
    }

    override fun findAllCountriesByDate(date: LocalDate): List<Population> {
        return adapter.getAllCountriesByDate(date)
    }
}
