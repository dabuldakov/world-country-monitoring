package org.wcm.domain.api

import org.wcm.domain.model.RefillFeatureStatus

interface RefillStatusAdapter {

    fun saveAll(statuses: List<RefillFeatureStatus>)

    fun getAll(): List<RefillFeatureStatus>
}