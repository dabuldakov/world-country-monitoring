package org.wcm.controller

class PathConstant {
    companion object {
        const val VO = "/v0"
        const val BASE = "/api/wcm$VO"
        const val INTERNATIONAL_RESERVE = "$BASE/international-reserve"
        const val GROSS_DOMESTIC_PRODUCT = "$BASE/gross-domestic-product"
    }
}