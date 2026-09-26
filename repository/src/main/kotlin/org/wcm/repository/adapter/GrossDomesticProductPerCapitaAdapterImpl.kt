package org.wcm.repository.adapter

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.wcm.domain.api.GrossDomesticProductPerCapitaAdapter
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.repository.GrossDomesticProductPerCapitaRepository
import org.wcm.repository.mapper.GrossDomesticProductPerCapitaMapper
import java.time.LocalDate

@Component
class GrossDomesticProductPerCapitaAdapterImpl(
    private val repository: GrossDomesticProductPerCapitaRepository,
    private val mapper: GrossDomesticProductPerCapitaMapper
) : GrossDomesticProductPerCapitaAdapter {

    @Transactional(readOnly = true)
    override fun getByCountryCode(countryCode: String): List<GrossDomesticProductPerCapita> =
        repository.findAllByCountryCodeOrderByDate(countryCode).map { mapper.toDomain(it) }

    @Transactional(readOnly = true)
    override fun getAllCountriesByYear(data: LocalDate): List<GrossDomesticProductPerCapita> =
        repository.findAllByDateOrderByAmount(data).map { mapper.toDomain(it) }

    override fun saveAll(items: List<GrossDomesticProductPerCapita>) {
        items.forEach { item ->
            if (item.amount != null) {
                repository.findFirstByCountryCodeAndDate(item.countryCode, item.date)?.let { entity ->
                    repository.save(mapper.updateAmount(entity, item.amount!!))
                } ?: repository.save(mapper.toEntity(item))
            }
        }
    }
}