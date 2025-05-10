package org.wcm.rest.client.worldbank.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.rest.client.worldbank.model.WorldBankModel
import java.time.LocalDate

@Component
class WorldBankMapper {

    fun toDomainCurrentGDP(worldBankModel: WorldBankModel, countryCode: String): List<GrossDomesticProduct> {
        return worldBankModel.value.map { data ->
            GrossDomesticProduct(
                current = doubleValue(data.value),
                countryCode = countryCode,
                date = convertYearToLocalDate(data.year)
            )
        }
    }

    fun toDomainPercentageToGDP(worldBankModel: WorldBankModel, countryCode: String): List<Debt> {
        return worldBankModel.value.map { data ->
            Debt(
                percentageToGDP = doubleValue(data.value),
                countryCode = countryCode,
                date = convertYearToLocalDate(data.year)
            )
        }
    }

    private fun convertYearToLocalDate(year: String): LocalDate =
        LocalDate.of(year.toInt(), 12, 1)

    private fun doubleValue(number: String): Double? =
        try {
            number.toDouble()
        } catch (e: NumberFormatException) {
            println("Invalid number format: $number")
            null
        }
}