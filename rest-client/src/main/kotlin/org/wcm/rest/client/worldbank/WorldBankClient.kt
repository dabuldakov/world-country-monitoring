package org.wcm.rest.client.worldbank

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.wcm.rest.client.worldbank.Constant.Companion.API_URL
import org.wcm.rest.client.worldbank.Constant.Companion.DATA360_PATH
import org.wcm.rest.client.worldbank.model.WorldBankModel
import java.net.URI
import java.net.URLEncoder

@Component
class WorldBankClient(
    @Autowired private val restTemplate: RestTemplate
) {
    private val logger: Logger = LoggerFactory.getLogger(WorldBankClient::class.java);

    fun getAllHistoryGDPbyCountry(countryCode: String): WorldBankModel? {
        return try {
            restTemplate.getForObject(buildGDPHistoriesUrl(countryCode), WorldBankModel::class.java)
        } catch (e: Exception) {
            logger.error("Error while getAllHistoryGDPbyCountry $countryCode", e)
            null
        }
    }

    fun getAllHistoryPercentageDebtToGDPbyCountry(countryCode: String): WorldBankModel? {
        return try {
            restTemplate.getForObject(buildDebtPercentageToGDPUrl(countryCode), WorldBankModel::class.java)
        } catch (e: Exception) {
            logger.error("Error while getPercentageDebtToGDPbyCountry $countryCode", e)
            null
        }
    }

    private fun buildGDPHistoriesUrl(countryCode: String): String {
        val queryParams = StringBuilder()
        queryParams.append("${Constant.DATABASE_ID}=${URLEncoder.encode(Constant.DATABASE_WB, "UTF-8")}&")
        queryParams.append("${Constant.INDICATOR}=${URLEncoder.encode(Constant.INDICATOR_GDP, "UTF-8")}&")
        queryParams.append("${Constant.REF_AREA}=${URLEncoder.encode(countryCode, "UTF-8")}&")
        queryParams.append("${Constant.skip}=0")

        return URI.create("$API_URL$DATA360_PATH?$queryParams").toURL().toString()
    }

    private fun buildDebtPercentageToGDPUrl(countryCode: String): String {
        val queryParams = StringBuilder()
        queryParams.append("${Constant.DATABASE_ID}=${URLEncoder.encode(Constant.DATABASE_WB_CCDFS, "UTF-8")}&")
        queryParams.append(
            "${Constant.INDICATOR}=${
                URLEncoder.encode(
                    Constant.INDICATOR_DEPT_PERCENTAGE_GDP,
                    "UTF-8"
                )
            }&"
        )
        queryParams.append("${Constant.REF_AREA}=${URLEncoder.encode(countryCode, "UTF-8")}&")
        queryParams.append("${Constant.skip}=0")

        return URI.create("$API_URL$DATA360_PATH?$queryParams").toURL().toString()
    }
}