package org.wcm.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.wcm.repository.entity.VisitCounterEntity

@Repository
interface VisitCounterRepository : JpaRepository<VisitCounterEntity, Long> {

    @Modifying
    @Query("UPDATE VisitCounterEntity v SET v.total = v.total + 1 WHERE v.id = 1")
    fun incrementTotal()
}
