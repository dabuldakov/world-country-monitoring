package org.wcm.rest.client.worldbank.mapper

import org.springframework.stereotype.Component
import org.wcm.domain.model.GrossDomesticProduct
import org.wcm.rest.client.worldbank.model.GDPModel
import org.wcm.rest.client.worldbank.model.GDPValue
import java.time.LocalDate

@Component
class WorldBankMapper {

    fun toDomain(gdpModel: GDPModel, countryCode: String): List<GrossDomesticProduct> {
        return gdpModel.value.map { data ->
            GrossDomesticProduct(
                current = data.OBS_VALUE.toDouble(),
                countryCode = countryCode,
                date = convertYearToLocalDate(data)
            )
        }
    }

    private fun convertYearToLocalDate(data: GDPValue): LocalDate =
        LocalDate.of(data.TIME_PERIOD.toInt(), 12, 1)
}