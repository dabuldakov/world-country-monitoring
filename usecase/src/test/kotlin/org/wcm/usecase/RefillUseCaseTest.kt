package org.wcm.usecase

import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.DebtAdapter
import org.wcm.domain.api.GrossDomesticProductAdapter
import org.wcm.domain.api.InternationalReserveAdapter
import org.wcm.domain.api.LifeExpectancyAdapter
import org.wcm.domain.api.PopulationAdapter
import org.wcm.domain.api.WorldBankApi
import org.wcm.domain.model.Country
import org.wcm.domain.model.Population
import java.time.LocalDate
import kotlin.test.Test

class RefillUseCaseTest {

    private val worldBankApi = mock<WorldBankApi>()
    private val countryAdapter = mock<CountryAdapter>()
    private val grossDomesticProductAdapter = mock<GrossDomesticProductAdapter>()
    private val debtAdapter = mock<DebtAdapter>()
    private val internationalReserveAdapter = mock<InternationalReserveAdapter>()
    private val populationAdapter = mock<PopulationAdapter>()
    private val lifeExpectancyAdapter = mock<LifeExpectancyAdapter>()
    private val useCase = RefillUseCase(
        worldBankApi,
        countryAdapter,
        grossDomesticProductAdapter,
        debtAdapter,
        internationalReserveAdapter,
        populationAdapter,
        lifeExpectancyAdapter
    )

    @Test
    fun `refill for one country saves all data including population`() {
        val populations = listOf(
            Population(
                population = 143513328.0,
                countryCode = "RUS",
                date = LocalDate.of(2025, 12, 1)
            )
        )
        whenever(worldBankApi.getAllHistoryGDPbyCountry("RUS")).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryPercentageToGDPByCountry("RUS")).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryReservesAmountByCountry("RUS")).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryPopulationByCountry("RUS")).thenReturn(populations)
        whenever(worldBankApi.getAllHistoryLifeExpectancyByCountry("RUS")).thenReturn(emptyList())

        useCase.forCountry("RUS")

        verify(populationAdapter).saveAll(populations)
    }

    @Test
    fun `refill for all countries processes every country`() {
        whenever(countryAdapter.getAll()).thenReturn(
            listOf(
                Country("RUS", "Russia"),
                Country("USA", "United States")
            )
        )
        whenever(worldBankApi.getAllHistoryGDPbyCountry(org.mockito.kotlin.any())).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryPercentageToGDPByCountry(org.mockito.kotlin.any())).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryReservesAmountByCountry(org.mockito.kotlin.any())).thenReturn(emptyList())
        whenever(worldBankApi.getAllHistoryPopulationByCountry(org.mockito.kotlin.any())).thenReturn(emptyList())

        useCase.forAllCountries()

        verify(worldBankApi).getAllHistoryPopulationByCountry("RUS")
        verify(worldBankApi).getAllHistoryPopulationByCountry("USA")
    }
}
