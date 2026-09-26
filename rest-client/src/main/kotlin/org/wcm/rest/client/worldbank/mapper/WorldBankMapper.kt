package org.wcm.rest.client.worldbank.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.Utils
import org.wcm.domain.model.Debt
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.domain.model.GrossDomesticProductPerCapita
import org.wcm.domain.model.InternationalReserve
import org.wcm.domain.model.LifeExpectancy
import org.wcm.domain.model.Population
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

    fun toDomainGDPPerCapita(worldBankModel: WorldBankModel, countryCode: String): List<GrossDomesticProductPerCapita> {
        return worldBankModel.value.map { data ->
            GrossDomesticProductPerCapita(
                amount = doubleValue(data.value),
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

    fun toDomainDebtAmount(worldBankModel: WorldBankModel, countryCode: String): List<Debt> {
        return worldBankModel.value.map { data ->
            Debt(
                foreign = doubleValue(data.value),
                countryCode = countryCode,
                date = Utils.convertYearToLocalDate(data.year)
            )
        }
    }

    fun toDomainReservesAmount(worldBankModel: WorldBankModel, countryCode: String): List<InternationalReserve> {
        return worldBankModel.value.map { data ->
            InternationalReserve(
                amount = doubleValue(data.value),
                countryCode = countryCode,
                date = Utils.convertYearToLocalDate(data.year)
            )
        }
    }

    fun toDomainPopulation(worldBankModel: WorldBankModel, countryCode: String): List<Population> {
        return worldBankModel.value.map { data ->
            Population(
                population = doubleValue(data.value),
                countryCode = countryCode,
                date = Utils.convertYearToLocalDate(data.year)
            )
        }
    }

    fun toDomainLifeExpectancy(worldBankModel: WorldBankModel, countryCode: String): List<LifeExpectancy> {
        return worldBankModel.value.map { data ->
            LifeExpectancy(
                years = doubleValue(data.value),
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
