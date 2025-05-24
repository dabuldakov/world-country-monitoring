package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.wcm.domain.api.MoneySupplyAdapter
import org.wcm.domain.model.MoneySupply
import org.wcm.repository.MoneySupplyRepository
import org.wcm.repository.mapper.MoneySupplyMapper
import java.time.LocalDate

@Component
class MoneySupplyAdapterImpl(
    val repository: MoneySupplyRepository,
    val mapper: MoneySupplyMapper
) : MoneySupplyAdapter {
    override fun findAllByCountryCode(countryCode: String): List<MoneySupply> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    override fun findAllCountriesByDate(date: LocalDate): List<MoneySupply> {
        return repository.findAllByDateOrderByAmountUsd(date).map { mapper.toDomain(it) }
    }
}
