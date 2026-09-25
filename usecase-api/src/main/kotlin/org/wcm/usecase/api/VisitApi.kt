package org.wcm.usecase.api

import org.wcm.domain.model.VisitStatistics

interface VisitApi {

    fun registerVisit(): VisitStatistics

    fun getStatistics(): VisitStatistics
}
