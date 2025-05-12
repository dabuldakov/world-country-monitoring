package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.InternationalReserveAdapter
import org.wcm.domain.exception.EntityNotFoundException
import org.wcm.domain.model.InternationalReserve
import org.wcm.repository.InternationalReserveRepository
import org.wcm.repository.mapper.InternationalReserveMapper

@Component
class InternationalReserveAdapterImpl(
    private val repository: InternationalReserveRepository,
    private val mapper: InternationalReserveMapper
) : InternationalReserveAdapter {

    @Transactional(readOnly = true)
    override fun getById(id: Long): InternationalReserve {
        return repository.findById(id).orElseThrow { EntityNotFoundException("Not found $id") }
            .let { mapper.toDomain(it) }
    }

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<InternationalReserve> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    override fun saveAll(reserves: List<InternationalReserve>) {
        reserves.forEach { item ->
            if (item.amount != null) {
                repository.findFirstByCountryCodeAndDate(item.countryCode, item.date)?.let { entity ->
                    val updateCurrent = mapper.updateAmount(entity, item.amount!!)
                    repository.save(updateCurrent)
                } ?: repository.save(mapper.toEntity(item))
            }
        }
    }
}