package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.model.DebtGross
import org.wcm.usecase.api.DebtGrossApi

@Component
class DebtGrossUseCase(
    private val debtAdapter: DebtAdapter,
    private val grossDomesticProductAdapter: GrossDomesticProductAdapter,
    private val debtGrossMapper: DebtGrossMapper,
) : DebtGrossApi {

    override fun getByCountryCode(countryCode: String): List<DebtGross> {
        val localDateDebtMap = debtAdapter.getByCountryCode(countryCode).associateBy { it.date }
        return grossDomesticProductAdapter.getByCountryCode(countryCode).mapNotNull { grossDomesticProduct ->
            val debt = localDateDebtMap[grossDomesticProduct.date]
            if (debt != null) {
                if (grossDomesticProduct.current != null &&
                    debt.foreign != null &&
                    grossDomesticProduct.current != 0.0
                ) {
                    DebtGross(
                        ratioPercentage = debt.foreign!! / grossDomesticProduct.current!! * 100,
                        date = debt.date
                    )
                } else {
                    null
                }
            } else null
        }
    }

    override fun getByCountryFromDataBase(countryCode: String): List<DebtGross> {
        return debtAdapter.getByCountryCode(countryCode)
            .filter { it.percentageToGDP != null }
            .map { debtGrossMapper.toDebtGross(it) }
    }
}