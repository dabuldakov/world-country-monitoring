package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.DeptAdapter
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.model.DeptGross
import org.wcm.usecase.api.DeptGrossApi
import java.util.Collections.emptyList

@Component
class DeptGrossUseCase(
    private val deptAdapter: DeptAdapter,
    private val grossDomesticProductAdapter: GrossDomesticProductAdapter
): DeptGrossApi {

    override fun getByCountryCode(countryCode: String): List<DeptGross> {
        val localDateDeptMap = deptAdapter.getByCountryCode(countryCode).associateBy { it.date }
        return grossDomesticProductAdapter.getByCountryCode(countryCode).mapNotNull { grossDomesticProduct ->
            val dept = localDateDeptMap[grossDomesticProduct.date]
            if (dept != null) {
                if (grossDomesticProduct.absolut != null &&
                    dept.foreign != null &&
                    grossDomesticProduct.absolut != 0.0
                ) {
                    DeptGross(
                        ratioPercentage = dept.foreign!! / grossDomesticProduct.absolut!! * 100,
                        date = dept.date
                    )
                } else {
                    null
                }
            } else null
        }
    }
}