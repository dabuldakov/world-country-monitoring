package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.model.Debt
import org.wcm.repository.DebtRepository
import org.wcm.repository.mapper.DebtMapper

@Component
class DebtAdapterImpl(
    private val repository: DebtRepository,
    private val mapper: DebtMapper
) : DebtAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<Debt> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    override fun saveAll(debts: List<Debt>) {
        debts.forEach { debt ->
            if (debt.percentageToGDP != null) {
                repository.findFirstByCountryCodeAndDate(debt.countryCode, debt.date)?.let { debtEntity ->
                    val updated = mapper.updatePercentageToGDP(debtEntity, debt.percentageToGDP!!)
                    repository.save(updated)
                } ?: repository.save(mapper.toEntity(debt))
            }
        }
    }
}