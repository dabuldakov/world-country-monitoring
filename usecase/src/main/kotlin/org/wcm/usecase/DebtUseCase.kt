package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.model.Debt
import org.wcm.usecase.api.DebtApi
import java.time.LocalDate

@Component
class DebtUseCase(
    private val adapter: DebtAdapter
) : DebtApi {

    override fun getByCountryCode(countryCode: String): List<Debt> {
        return adapter.getByCountryCode(countryCode)
    }

    override fun getAllCountriesByYear(date: LocalDate): List<Debt> {
        return adapter.getAllCountriesByYear(date)
    }
}