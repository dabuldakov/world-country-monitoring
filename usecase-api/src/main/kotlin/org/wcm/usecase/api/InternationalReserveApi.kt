package org.wcm.usecase.api

import org.wcm.domain.model.InternationalReserve

interface InternationalReserveApi {
    fun getBiId(id: Long): InternationalReserve
    fun getByCountryCode(countryCode: String): List<InternationalReserve>
}