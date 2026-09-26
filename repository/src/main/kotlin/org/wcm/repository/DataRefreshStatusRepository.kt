package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.DataRefreshStatusEntity

@Repository
interface DataRefreshStatusRepository : JpaRepository<DataRefreshStatusEntity, String>