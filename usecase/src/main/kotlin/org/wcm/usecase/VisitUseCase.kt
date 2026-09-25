package org.wcm.usecase

import org.springframework.stereotype.Component
import org.wcm.domain.api.VisitAdapter
import org.wcm.domain.model.VisitStatistics
import org.wcm.usecase.api.VisitApi

@Component
class VisitUseCase(
    private val visitAdapter: VisitAdapter
) : VisitApi {

    override fun registerVisit(): VisitStatistics = visitAdapter.registerVisit()

    override fun getStatistics(): VisitStatistics = visitAdapter.getStatistics()
}
