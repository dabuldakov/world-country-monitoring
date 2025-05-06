package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.DeptAdapter
import org.wcm.domain.model.Dept
import org.wcm.usecase.api.DeptApi

@Component
class DeptUseCase(
    private val adapter: DeptAdapter
): DeptApi {

    override fun getByCountryCode(countryCode: String): List<Dept> {
        return adapter.getByCountryCode(countryCode)
    }
}