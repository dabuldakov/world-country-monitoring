package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.InternationalReserveAdapter
import org.wcm.domain.model.InternationalReserve
import org.wcm.usecase.api.InternationalReserveApi

@Component
class InternationalReserveUseCase(
    private val adapter: InternationalReserveAdapter
): InternationalReserveApi {

    override fun getBiId(id: Long): InternationalReserve {
        return adapter.getById(id)
    }

    override fun getByCountryCode(countryCode: String): List<InternationalReserve> {
        return adapter.getByCountryCode(countryCode)
    }
}