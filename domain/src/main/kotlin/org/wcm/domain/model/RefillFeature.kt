package org.wcm.domain.model

enum class RefillFeature(val key: String) {
    GDP("gdp"),
    DEBT("debt"),
    RESERVES("reserves"),
    POPULATION("population");

    companion object {
        fun fromKey(key: String): RefillFeature? =
            entries.firstOrNull { it.key.equals(key, ignoreCase = true) }
    }
}