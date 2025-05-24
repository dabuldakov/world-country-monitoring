package org.wcm.controller

class PathConstant {
    companion object {
        const val VO = "/v0"
        const val BASE = "/api/wcm$VO"
        const val INTERNATIONAL_RESERVE = "$BASE/international-reserve"
        const val GROSS_DOMESTIC_PRODUCT = "$BASE/gross-domestic-product"
        const val MONEY_SUPPLY = "$BASE/money-supply"
        const val DEBT = "$BASE/debt"
        const val COUNTRY = "$BASE/country"
        const val SCHEDULER = "$BASE/scheduler"
    }
}