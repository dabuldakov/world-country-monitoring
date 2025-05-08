package org.wcm.rest.client.worldbank.model

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class GDPValue @JsonCreator constructor(
    @JsonProperty("OBS_VALUE") val value: String,
    @JsonProperty("TIME_PERIOD") val year: String,
)