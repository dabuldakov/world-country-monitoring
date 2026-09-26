package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.model.Debt
import org.wcm.repository.DebtRepository
import org.wcm.repository.entity.DebtEntity
import org.wcm.repository.mapper.DebtMapper
import java.time.LocalDate

@Component
class DebtAdapterImpl(
    private val repository: DebtRepository,
    private val mapper: DebtMapper
) : DebtAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<Debt> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllCountriesByYear(data: LocalDate): List<Debt> {
        return repository.findAllByDateOrderByPercentageToGDP(data).map { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun getAllCountriesAmountByYear(data: LocalDate): List<Debt> {
        return repository.findAllByDateOrderByForeign(data).map { mapper.toDomain(it) }
    }

    override fun saveAll(debts: List<Debt>) {
        debts.forEach { debt ->
            val existing = repository.findFirstByCountryCodeAndDate(debt.countryCode, debt.date)
            if (existing != null) {
                val updated = updateExisting(existing, debt)
                repository.save(updated)
            } else if (debt.foreign != null || debt.percentageToGDP != null) {
                repository.save(mapper.toEntity(debt))
            }
        }
    }

    private fun updateExisting(existing: DebtEntity, debt: Debt): DebtEntity {
        var updated = existing
        debt.percentageToGDP?.let { updated = mapper.updatePercentageToGDP(updated, it) }
        debt.foreign?.let { updated = mapper.updateForeign(updated, it) }
        return updated
    }
}
