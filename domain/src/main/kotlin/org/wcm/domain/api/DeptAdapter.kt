package org.wcm.domain.api

import org.wcm.domain.model.Dept

interface DeptAdapter {
    fun getByCountryCode(countryCode: String): List<Dept>
}