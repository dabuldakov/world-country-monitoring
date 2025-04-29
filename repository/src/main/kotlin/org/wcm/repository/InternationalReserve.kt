package org.wcm.repository

import org.wcm.entity.InternationalReserve
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InternationalReserve : JpaRepository<InternationalReserve, Long> {
}