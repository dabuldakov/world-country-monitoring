package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.repository.GrossDomesticProductRepository
import org.wcm.repository.mapper.GrossDomesticProductMapper

@Component
class GrossDomesticProductAdapterImpl(
    val repository: GrossDomesticProductRepository,
    val mapper: GrossDomesticProductMapper
) : GrossDomesticProductAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<GrossDomesticProduct> {
        return repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }
    }

    override fun saveAll(gDPs: List<GrossDomesticProduct>) {
        gDPs.forEach { gDP ->
            if (gDP.current!= null) {
                repository.findFirstByCountryCodeAndDate(gDP.countryCode, gDP.date)?.let { gDPEntity ->
                    val updateCurrent = mapper.updateCurrent(gDPEntity, gDP.current!!)
                    repository.save(updateCurrent)
                } ?: repository.save(mapper.toEntity(gDP))
            }
        }
    }
}