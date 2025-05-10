package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.model.Debt
import org.wcm.domain.model.DebtGross

@Component
class DebtGrossMapper {

    fun toDebtGross(debt: Debt) =
        DebtGross(
            ratioPercentage = debt.percentageToGDP!!,
            date = debt.date
        )
}