package org.wcm.usecase

import org.mockito.kotlin.any
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.clearInvocations
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import org.wcm.domain.api.CountryAdapter
import org.wcm.domain.api.RefillStatusAdapter
import org.wcm.domain.api.RefreshJobAdapter
import org.wcm.domain.model.Country
import org.wcm.domain.model.RefillFeature
import org.wcm.domain.model.RefreshJob
import org.wcm.domain.model.RefreshJobItem
import org.wcm.usecase.api.RefillApi
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class RefreshJobUseCaseTest {

    private val refreshJobAdapter = mock<RefreshJobAdapter>()
    private val refillApi = mock<RefillApi>()
    private val countryAdapter = mock<CountryAdapter>()
    private val refillStatusAdapter = mock<RefillStatusAdapter>()
    private val useCase = RefreshJobUseCase(
        refreshJobAdapter,
        refillApi,
        countryAdapter,
        refillStatusAdapter
    )

    @Test
    fun `enqueues job for all countries and returns existing active job`() {
        whenever(countryAdapter.getAll()).thenReturn(
            listOf(Country("RUS", "Russia"), Country("USA", "United States"))
        )
        whenever(refreshJobAdapter.saveJob(any())).thenAnswer {
            it.getArgument<RefreshJob>(0).copy(id = 10L)
        }

        val job = useCase.enqueue(RefillFeature.GDP, null)

        assertEquals(10L, job.id)
        assertEquals(2, job.total)
        verify(refreshJobAdapter).saveItems(any())

        clearInvocations(refreshJobAdapter)
        val existing = RefreshJob(id = 11L, feature = "gdp", status = "RUNNING")
        whenever(refreshJobAdapter.findActiveJob("gdp", null)).thenReturn(existing)

        assertEquals(existing, useCase.enqueue(RefillFeature.GDP, null))
        verify(refreshJobAdapter, never()).saveJob(any())
    }

    @Test
    fun `processes queued job and records failed country`() {
        val job = RefreshJob(id = 1L, feature = "population", status = "RUNNING", total = 2)
        whenever(refreshJobAdapter.claimNextQueuedJob()).thenReturn(job)
        whenever(refreshJobAdapter.findItems(1L)).thenReturn(
            listOf(
                RefreshJobItem(id = 1L, jobId = 1L, countryCode = "RUS", status = "PENDING"),
                RefreshJobItem(id = 2L, jobId = 1L, countryCode = "USA", status = "PENDING")
            )
        )
        doThrow(RuntimeException("world bank down"))
            .whenever(refillApi).forCountry(RefillFeature.POPULATION, "USA")

        assertTrue(useCase.processNextJob())

        verify(refillApi).forCountry(RefillFeature.POPULATION, "RUS")
        verify(refillApi).forCountry(RefillFeature.POPULATION, "USA")

        val savedJobs = argumentCaptor<RefreshJob>()
        verify(refreshJobAdapter, atLeastOnce()).saveJob(savedJobs.capture())
        val finalJob = savedJobs.allValues.last()
        assertEquals("PARTIAL", finalJob.status)
        assertEquals(2, finalJob.processed)
        assertEquals(1, finalJob.failed)
    }

    @Test
    fun `retry failed creates job only for failed countries`() {
        val job = RefreshJob(id = 1L, feature = "gdp", status = "PARTIAL", total = 2)
        whenever(refreshJobAdapter.findJobById(1L)).thenReturn(job)
        whenever(refreshJobAdapter.findItems(1L)).thenReturn(
            listOf(
                RefreshJobItem(id = 1L, jobId = 1L, countryCode = "RUS", status = "SUCCESS"),
                RefreshJobItem(id = 2L, jobId = 1L, countryCode = "USA", status = "FAILED")
            )
        )
        whenever(refreshJobAdapter.saveJob(any())).thenAnswer {
            it.getArgument<RefreshJob>(0).copy(id = 2L)
        }

        val retry = useCase.retryFailed(1L)!!

        assertNotEquals(1L, retry.id)
        assertEquals("gdp", retry.feature)
        assertEquals("USA", retry.countryCode)
        assertEquals(1, retry.total)
    }
}