package org.wcm.domain.api

import org.wcm.domain.model.VisitStatistics

interface VisitAdapter {

    fun registerVisit(): VisitStatistics

    fun getStatistics(): VisitStatistics
}
