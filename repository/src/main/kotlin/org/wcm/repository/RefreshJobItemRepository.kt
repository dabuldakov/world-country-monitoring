package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.RefreshJobItemEntity

@Repository
interface RefreshJobItemRepository : JpaRepository<RefreshJobItemEntity, Long> {

    fun findAllByJobIdOrderById(jobId: Long): List<RefreshJobItemEntity>

    fun findAllByJobIdAndStatusOrderById(jobId: Long, status: String): List<RefreshJobItemEntity>
}