package com.khaled.thmanyah.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val sections: List<Section>,
    val pagination: Pagination?=null
)