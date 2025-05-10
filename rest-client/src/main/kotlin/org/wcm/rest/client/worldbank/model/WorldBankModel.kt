package org.wcm.rest.client.worldbank.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class WorldBankModel @JsonCreator constructor(
    @JsonProperty("count") val count: Int,
    @JsonProperty("value") val value: List<WorldBankValue>
)


