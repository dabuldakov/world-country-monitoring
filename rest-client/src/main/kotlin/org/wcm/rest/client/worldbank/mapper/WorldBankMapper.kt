package org.wcm.rest.client.worldbank.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.Utils
import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.rest.client.worldbank.model.WorldBankModel

@Component
class WorldBankMapper {

    fun toDomainCurrentGDP(worldBankModel: WorldBankModel, countryCode: String): List<GrossDomesticProduct> {
        return worldBankModel.value.map { data ->
            GrossDomesticProduct(
                current = doubleValue(data.value),
                countryCode = countryCode,
                date = Utils.convertYearToLocalDate(data.year)
            )
        }
    }

    fun toDomainPercentageToGDP(worldBankModel: WorldBankModel, countryCode: String): List<Debt> {
        return worldBankModel.value.map { data ->
            Debt(
                percentageToGDP = doubleValue(data.value),
                countryCode = countryCode,
                date = Utils.convertYearToLocalDate(data.year)
            )
        }
    }

    private fun doubleValue(number: String): Double? =
        try {
            number.toDouble()
        } catch (e: NumberFormatException) {
            println("Invalid number format: $number")
            null
        }
}