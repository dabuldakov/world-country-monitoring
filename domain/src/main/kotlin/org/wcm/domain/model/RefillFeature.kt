package org.wcm.domain.model

enum class RefillFeature(val key: String) {
    GDP("gdp"),
    GDP_PER_CAPITA("gdp-per-capita"),
    DEBT("debt"),
    DEBT_AMOUNT("debt-amount"),
    RESERVES("reserves"),
    POPULATION("population"),
    LIFE_EXPECTANCY("life-expectancy");

    companion object {
        fun fromKey(key: String): RefillFeature? =
            entries.firstOrNull { it.key.equals(key, ignoreCase = true) }
    }
}