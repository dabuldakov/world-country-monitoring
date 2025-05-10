package org.wcm.domain

import java.time.LocalDate

class Utils {

    companion object {
        fun convertYearToLocalDate(year: String): LocalDate =
            LocalDate.of(year.toInt(), 12, 1)
    }
}