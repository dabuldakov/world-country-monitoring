package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.LifeExpectancyAdapter
import org.wcm.domain.model.LifeExpectancy
import org.wcm.repository.LifeExpectancyRepository
import org.wcm.repository.mapper.LifeExpectancyMapper
import java.time.LocalDate

@Component
class LifeExpectancyAdapterImpl(
    private val repository: LifeExpectancyRepository,
    private val mapper: LifeExpectancyMapper
) : LifeExpectancyAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<LifeExpectancy> =
        repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }

    @Transactional(readOnly = true)
    override fun getAllCountriesByYear(data: LocalDate): List<LifeExpectancy> =
        repository.findAllByDateOrderByYears(data).map { mapper.toDomain(it) }

    override fun saveAll(items: List<LifeExpectancy>) {
        items.forEach { item ->
            if (item.years != null) {
                repository.findFirstByCountryCodeAndDate(item.countryCode, item.date)?.let { entity ->
                    repository.save(mapper.updateYears(entity, item.years!!))
                } ?: repository.save(mapper.toEntity(item))
            }
        }
    }
}