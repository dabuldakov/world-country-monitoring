package org.wcm.domain.api

import org.wcm.domain.model.InternationalReserve

interface InternationalReserveAdapter {

    fun getById(id: Long): InternationalReserve

    fun getByCountryCode(countryCode: String): List<InternationalReserve>
}