package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.DeptAdapter
import org.wcm.domain.model.Dept
import org.wcm.repository.DeptRepository
import org.wcm.repository.mapper.DeptMapper

@Component
class DeptAdapterImpl(
    private val repository: DeptRepository,
    private val mapper: DeptMapper
) : DeptAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<Dept> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }
}