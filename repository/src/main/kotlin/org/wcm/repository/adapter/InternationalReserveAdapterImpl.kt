package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.InternationalReserveAdapter
import org.wcm.domain.exception.EntityNotFoundException
import org.wcm.repository.mapper.InternationalReserveMapper
import org.wcm.domain.model.InternationalReserve
import org.wcm.repository.InternationalReserveRepository

@Component
class InternationalReserveAdapterImpl(
    private val repository: InternationalReserveRepository,
    private val mapper: InternationalReserveMapper
) : InternationalReserveAdapter {

    @Transactional(readOnly = true)
    override fun getById(id: Long): InternationalReserve {
        return repository.findById(id).orElseThrow { EntityNotFoundException("Not found $id") }.let { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<InternationalReserve> {
        return repository.findAllByCountryCode(countryCode).map { mapper.toDomain(it) }
    }
}