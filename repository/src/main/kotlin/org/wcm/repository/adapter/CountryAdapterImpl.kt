package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.model.Country
import org.wcm.repository.CountryRepository
import org.wcm.repository.mapper.CountryMapper

@Component
class CountryAdapterImpl(
    private val repository: CountryRepository,
    private val mapper: CountryMapper
): CountryAdapter {

    @Transactional(readOnly = true)
    override fun getAll(): List<Country> {
        return repository.findAllByOrderByNameAsc().map { mapper.toDomain(it) }
    }
}